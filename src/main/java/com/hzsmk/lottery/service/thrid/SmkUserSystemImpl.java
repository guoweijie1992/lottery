package com.hzsmk.lottery.service.thrid;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hzsmk.common.exception.BusinessException;
import com.hzsmk.common.util.TLocalHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Guowj
 * @Classname UserSystemImpl
 * @Date2021 2021/8/30 0030 15:14
 */
@Service
@Slf4j
public class SmkUserSystemImpl implements SmkUserSystem{

    @Autowired
    private Environment env;

    private final static Map<String, String> commemt = new HashMap<String, String>() {
        {
            put("TRA003010117", "登录");
            put("TRA003010101", "注册");
            put("TRA003010119", "samid校验");
            put("TRA003010118", "登出");
            put("TRA003010111", "添加卡");
            put("TRA003010112", "解除卡");
            put("TRA003010107", "关联用户");
            put("TRA003010108", "解除关联用户");
            put("TRA003010109", "获取用户关联信息");
            put("TRA003010110", "查询主用户");
            put("TRA003010105", "获取用户简单信息");
            put("TRA003010103", "获取用户信息");
            put("TRA003010106", "修改或重置密码");
            put("TRA003010123", "查询用户卡信息");
            put("TRA003010124", "获取用户客户信息");
            put("TRA003010102", "更新用户信息");
            put("TRA003010128", "检查用户是否存在");
            put("TRA003010122", "获取用户关键信息");
            put("TRA003010146", "免密注册");
            put("TRA003010147", "发送验证码登录申请");
            put("TRA003010148", "免密登录");
            put("TRA003010152", "手机验证码发送");
            put("TRA003010153", "手机验证码验证");
            put("TRA003010130", "重置登录密码");
            put("TRA003010147", "短信登录发送");
            put("TRA003010148", "短信登录");
        }
    };

    public Map<String, Object> postUserSystem(String si, Map<String, String> param)  {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, String> head = new HashMap<String, String>();
        head.put("_cn", "IFSP01");
        head.put("_tn", TLocalHelper.getSeq());// 本地流水号
        head.put("_si", si);
        head.put("_iName", commemt.get(si));
        String rep = HttpUtil.createPost(env.getProperty("userSystem.url")).headerMap( head, true).body(JSONUtil.toJsonStr(param)).execute().body();
        ret = JSONUtil.toBean(rep,Map.class);
        return ret;
    }

    /**
     * 手机号获取用户userId
     * @param mobile
     * @return
     */
    @Override
    public String getUserIdByMobile(String mobile) {
        //调用统一用户校验用户是否存在
        Map<String, String> param = new HashMap<String, String>();
        param.put("user_id", mobile);
        param.put("user_name_type", "1");
        Map<String, Object> ret = postUserSystem("TRA003010122", param);
        String userId = null;
        if ("0004".equals(ret.get("code"))) {
            throw new BusinessException("助力手机号不存在");
        } else if ("0000".equals(ret.get("code"))) {
            //代表用户已注册根据userId调用token生成
            userId = ((Map<String, String>) ((List) ret.get("list")).get(0)).get("user_id");
            return userId;
        } else {
            log.info(JSONUtil.toJsonStr(ret));
            throw new BusinessException("获取用户信息失败");
        }
    }

    /**
     * userId获取用户信息
     * @param userId
     * @return
     */
    @Override
    public Map getUserInfo(String userId) {
        //调用统一用户校验用户是否存在
        Map<String, String> param = new HashMap<String, String>();
        Map<String, String> retMap = new HashMap<String, String>();
        param.put("user_id", userId);
        Map<String, Object> ret = postUserSystem("TRA003010105", param);
        if (!"0000".equals(ret.get("code"))) {
            throw new BusinessException("获取用户信息失败");
        } else {
            List userList = (List) ret.get("list");
            Map<String, String> listMap = (Map<String, String>) userList.get(0);
            retMap.put("mobile", (String) listMap.get("mobile"));
            retMap.put("nickname", (String) listMap.get("nickname"));
            retMap.put("name", (String) listMap.get("name"));
            retMap.put("idCard", (String) listMap.get("id_no"));
            retMap.put("lev", (String) listMap.get("user_level"));
            retMap.put("headImageUrl", (String) listMap.get("head_img_url"));
            retMap.put("userId", userId);
        }
        return retMap;
    }
}
