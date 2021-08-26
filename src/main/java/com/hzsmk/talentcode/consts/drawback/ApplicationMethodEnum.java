package com.hzsmk.talentcode.consts.drawback;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人才类别
 *
 * @author shenyx
 * @date 2020/11/27 19:30
 */
public enum ApplicationMethodEnum {

    APPLICATION_METHOD_ONLINE("1", "在线"),
    APPLICATION_METHOD_WINDOW("2", "窗口");

    private String applicationMethod;
    private String desc;

    ApplicationMethodEnum(String applicationMethod, String desc) {
        this.applicationMethod = applicationMethod;
        this.desc = desc;
    }

    public String getApplicationMethod() {
        return applicationMethod;
    }

    public String getDesc() {
        return desc;
    }


    private static List<Map<String, String>> resultAll = new ArrayList<>();


    static {
        for (ApplicationMethodEnum e : ApplicationMethodEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("applicationMethod", e.getApplicationMethod());
            map.put("desc", e.getDesc());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有
     *
     * @return
     */
    public static List<Map<String, String>> getAllType() {
        return resultAll;
    }

    /**
     * 根据code获取对应desc
     *
     * @return
     */
    public static String getDescByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (ApplicationMethodEnum e : ApplicationMethodEnum.values()) {
                if (e.getApplicationMethod().equals(code)) {
                    return e.getDesc();
                }
            }
        }
        return "";
    }
}
