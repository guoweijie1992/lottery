package com.hzsmk.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 线程缓存工具
 *
 * @author yaojn
 */
public class TLocalHelper {

    private static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<Map<String, String>>();

    public static void closeLocal() {
        threadLocal.remove();
    }

    private static class LastTime {
        private static long time;
        private static int i;
    }

    /**
     * 创建流水
     *
     * @return
     */
    public static String createSeq() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String sequence = null;
        long nowSec = now.getTime() / 1000;
        synchronized (LastTime.class) {
            if (LastTime.time >= nowSec) {
                LastTime.i++;
            } else {
                LastTime.i = 0;
                LastTime.time = nowSec;
            }
            sequence = "25" + df.format(new Date(LastTime.time * 1000))
                    + String.format("%04d", LastTime.i);
        }
        Map<String, String> tlMap = new HashMap<String, String>();
        tlMap.put("localseq", sequence);
        threadLocal.set(tlMap);
        return sequence;
    }


    /**
     * 设置异步线程流水
     *
     * @return
     */
    public static String setTaskSeq(String seq) {
        Map<String, String> tlMap = new HashMap<String, String>();
        tlMap.put("localseq", seq);
        threadLocal.set(tlMap);
        return seq;
    }


    /**
     * 获取流水
     *
     * @return
     */
    public static String getSeq() {
        Map<String, String> map = threadLocal.get();
        if (Objects.nonNull(map)) {
            return map.get("localseq");
        } else {
            return "";
        }
    }

    public static Map getAll() {
        return threadLocal.get();
    }

    /**
     * 获取数据
     *
     * @return
     */
    public static String getData() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("requestData") != null ? tlMap.get("requestData") : "";
    }

    /**
     * 设置数据
     *
     * @param requestData
     */
    public static void setData(String requestData) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("requestData", requestData);
    }

    /**
     * 获取外部流水�?
     *
     * @return
     */
    public static String getSerialSeq() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("serialSeq") != null ? tlMap.get("serialSeq") : "";
    }

    /**
     * 设置外部流水
     *
     * @param serialSeq
     */
    public static void setSerialSeq(String serialSeq) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("serialSeq", serialSeq);
    }


    /**
     * 获取应用版本�?
     *
     * @return
     */
    public static String getVerChl() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("verChl") != null ? tlMap.get("verChl") : "";
    }

    /**
     * 设置应用版本�?
     *
     * @param verChl
     */
    public static void setVerChl(String verChl) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("verChl", verChl);
    }


    /**
     * 获取应用�?
     *
     * @return
     */
    public static String getSendChl() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("sendChl") != null ? tlMap.get("sendChl") : "";
    }

    /**
     * 设置应用�?
     *
     * @param sendChl
     */
    public static void setSendChl(String sendChl) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("sendChl", sendChl);
    }


    /**
     * 获取设备�?
     *
     * @return
     */
    public static String getSendDev() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("sendDev") != null ? tlMap.get("sendDev") : "";
    }

    /**
     * 设置设备�?
     *
     * @param sendDev
     */
    public static void setSendDev(String sendDev) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("sendDev", sendDev);
    }


    /**
     * 获取用户验证�?
     *
     * @return
     */
    public static String getSendClient() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("sendClient") != null ? tlMap.get("sendClient") : "";
    }

    /**
     * 设置用户验证�?
     *
     * @param sendClient
     */
    public static void setSendClient(String sendClient) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("sendClient", sendClient);
    }


    /**
     * 获取用户Id
     *
     * @return
     */
    public static String getUserId() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("userId") != null ? tlMap.get("userId") : "";
    }

    /**
     * 设置用户Id
     *
     * @param userId
     */
    public static void setUserId(String userId) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("userId", userId);
    }


    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("accessToken") != null ? tlMap.get("accessToken") : "";
    }

    /**
     * 设置token
     *
     * @param
     */
    public static void setToken(String accessToken) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("accessToken", accessToken);
    }


    /**
     * 获取接口地址
     *
     * @return
     */
    public static String getReqUrl() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("reqUri") != null ? tlMap.get("reqUri") : "";
    }

    /**
     * 设置接口地址
     *
     * @param
     */
    public static void setReqUrl(String reqUri) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("reqUri", reqUri);
    }


    /**
     * 终端类型
     *
     * @return
     */
    public static String getTerminal() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("terminal") != null ? tlMap.get("terminal") : "";
    }

    /**
     * 设置终端类型
     *
     * @param
     */
    public static void setTerminal(String terminal) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("terminal", terminal);
    }


    /**
     * 获取错误返回码
     *
     * @return
     */
    public static String getMsgCode() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("msgCode") != null ? tlMap.get("msgCode") : "";
    }

    /**
     * 设置错误返回码
     *
     * @param
     */
    public static void setMsgCode(String msgCode) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("msgCode", msgCode);
    }


    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUserName() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("userName") != null ? tlMap.get("userName") : "";
    }

    /**
     * 设置用户名
     *
     * @param userName
     */
    public static void setUserName(String userName) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("userName", userName);
    }


    /**
     * 获取app版本
     *
     * @return
     */
    public static String getVersion() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("version") != null ? tlMap.get("version") : "";
    }

    /**
     * 设置app版本
     *
     * @param version
     */
    public static void setVersion(String version) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("version", version);
    }

    /**
     * 设置ip
     *
     * @param ip
     */
    public static void setIp(String ip) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("ip", ip);
    }

    /**
     * 获取ip
     *
     * @return
     */
    public static String getIp() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("ip");
    }


    /**
     * 获取浏览器型号和版本
     *
     * @return
     */
    public static String getUseAgent() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("userAgent") != null ? tlMap.get("userAgent") : "";
    }

    /**
     * 设置浏览器型号和版本
     *
     * @param
     */
    public static void setUseAgent(String userAgent) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("userAgent", userAgent);
    }


    /**
     * 获取客户号
     *
     * @return
     */
    public static String getClientCode() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("clientCode") != null ? tlMap.get("clientCode") : "";
    }

    /**
     * 设置客户号
     *
     * @param
     */
    public static void setClientCode(String clientCode) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("clientCode", clientCode);
    }


    /**
     * 获取chlType(渠道类型：scan,market)
     *
     * @return
     */
    public static String getChlType() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("chlType") != null ? tlMap.get("chlType") : "";
    }

    /**
     * 设置chlType
     *
     * @param
     */
    public static void setChlType(String chlType) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("chlType", chlType);
    }


    /**
     * 获取chlName（渠道名:market-yyb）
     *
     * @return
     */
    public static String getChlName() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("chlName") != null ? tlMap.get("chlName") : "";
    }

    /**
     * 设置chlName
     *
     * @param
     */
    public static void setChlName(String chlName) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("chlName", chlName);
    }


    /**
     * 获取设备型号
     *
     * @return
     */
    public static String getDevice() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("device") != null ? tlMap.get("device") : "";
    }

    /**
     * 设置设备型号
     *
     * @param device
     */
    public static void setDevice(String device) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("device", device);
    }


    /**
     * 获取实际用户id
     *
     * @return
     */
    public static String getRealUserId() {
        Map<String, String> tlMap = threadLocal.get();
        return tlMap.get("realUserId") != null ? tlMap.get("realUserId") : "";
    }

    /**
     * 设置实际用户id
     *
     * @param realUserId
     */
    public static void setRealUserId(String realUserId) {
        Map<String, String> tlMap = threadLocal.get();
        tlMap.put("realUserId", realUserId);
    }
}
