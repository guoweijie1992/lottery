package com.hzsmk.common.util;

import com.hzsmk.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 解析市民卡APP用户的token
 *
 * @author jiangjh
 * @date 2020/4/3 11:11
 */
@Slf4j
@Component
public class SmkTokenUtil {

    /**
     * 市民卡app解析token的接口,code,key
     */
    @Value("${smk.appTokenUrl}")
    private String appTokenUrl;

    @Value("${smk.appClientCode}")
    private String appClientCode;

    @Value("${smk.appPrivateKey}")
    private String appPrivateKey;

    /**
     * SMK-APP
     */
    private static final String CHANNEL_APP = "app";
    /**
     * 成功code编号
     */
    private static final String SUCCESS_CODE = "PY0000";

    /**
     * 调用市民卡解析token接口返回的code
     */
    private static final String RESULT_CODE = "code";

    /**
     * 调用市民卡解析token接口返回的msg
     */
    private static final String RESULT_MSG = "msg";

    /**
     * 调用市民卡解析token接口返回的response
     */
    private static final String RESULT_RESSPONSE = "response";

    /**
     * 根据token解析用户信息
     *
     * @param token
     * @return
     */
    public SmkAppUser getAppUser(String token) {
        try {
            SmkAppUser user = getUserInfoByCode(token, CHANNEL_APP);
            if (StringUtils.isAnyBlank(user.getUserId(), user.getMobile())) {
                throw new BusinessException("获取用户信息失败，请稍后再试");
            }
            return user;
        } catch (Exception e) {
            log.info("system error:{}", e);
            throw new BusinessException("解析app-token失败");
        }
    }

    /**
     * 根据token,渠道获取解析用户信息(暂时只有SMK-APP)
     *
     * @param token
     * @param channel
     * @return
     * @throws Exception
     */
    public SmkAppUser getUserInfoByCode(String token, String channel) {
        SmkAppUser user = new SmkAppUser();
        Map<String, Object> retMap = getUserInfoByChannel(token, channel);
        if (CHANNEL_APP.equals(channel)) {
            if (SUCCESS_CODE.equalsIgnoreCase(String.valueOf(retMap.get(RESULT_CODE)))) {
                Map<String, Object> response = (Map<String, Object>) retMap.get(RESULT_RESSPONSE);
                if (Objects.isNull(response)) {
                    throw new RuntimeException("用户信息返回为空");
                }
                user.setMobile((String) response.get("mobile"));
                user.setName((String) response.get("name"));
                user.setIdCard((String) response.get("idCard"));
                user.setUserId((String) response.get("userId"));
                String idcard = user.getIdCard();
                if (StringUtils.isNotBlank(idcard)) {
                    user.setIdCard(idcard.toLowerCase());
                }
            } else {
                throw new RuntimeException((String) retMap.get(RESULT_MSG));
            }
        }
        return user;
    }

    private Map<String, Object> getUserInfoByChannel(String token, String channel) {
        String url;
        Map<String, String> header = new HashMap<>(8);
        Map<String, Object> params = new HashMap<>(8);
        if (CHANNEL_APP.equals(channel)) {
            url = appTokenUrl;
            header.put("clientCode", appClientCode);
            header.put("serialSeq", String.valueOf(System.currentTimeMillis()));
            header.put("Content-Type", "application/json");
            params.put("begTime", DateUtil.format(new Date(), "yyyyMMddHHmmssSSS"));
            params.put("accessToken", token);
            try {
                String sign = Rsa.sign(appPrivateKey, getAppTokenSign(params));
                params.put("sign", sign);
            } catch (Exception e) {
                log.info("system error:{}", e);
                throw new RuntimeException("Rsa.sign加密错误!");
            }

        } else {
            throw new RuntimeException("渠道错误");
        }
        Map<String, Object> retMap = requestUserInfo(url, params, header);
        return retMap;
    }

    private String getAppTokenSign(Map<String, Object> obj) {
        String sign = "";
        try {
            StringBuffer str = new StringBuffer();
            Collection<String> keyset = obj.keySet();
            List<String> list = new ArrayList<String>(keyset);
            Collections.sort(list);
            for (String key : list) {
                if ("sign".equals(key)) {
                    continue;
                }
                str.append(key).append("=").append(obj.get(key)).append("&");
            }
            str.deleteCharAt(str.length() - 1);
            sign = new String(str);
        } catch (Exception e) {
            log.error("erro:{}", e);
        }
        return sign;
    }

    /**
     * 调用接口
     *
     * @param url
     * @param params
     * @param header
     * @return
     */
    private Map<String, Object> requestUserInfo(String url, Map<String, Object> params, Map<String, String> header) {
        String res = HttpUtil.postHttplientJsonHeader(url, params, header);
        if (StringUtils.isBlank(res)) {
            return null;
        }
        Map<String, Object> retMap = JsonUtil.parse(res, Map.class);
        return retMap;
    }
}
