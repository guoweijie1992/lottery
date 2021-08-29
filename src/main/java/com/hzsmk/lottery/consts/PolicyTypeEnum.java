package com.hzsmk.lottery.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 政策类型
 *
 * @author jiangjh
 * @date 2020/4/9 13:49
 */
public enum PolicyTypeEnum {

    HOME("安家补贴", "ajbt"),
    WORK("创业扶持", "work"),
    SERVER("服务保障", "server"),
    MONEY("经济补贴", "money"),
    SCIENTIFIC("科研资助", "scientific"),
    TALENT("人才引进", "talent"),
    HOUSE("住房保障", "house"),
    OTHER("其他", "other");

    private String desc;
    private String descEng;

    PolicyTypeEnum(String desc, String descEng) {
        this.desc = desc;
        this.descEng = descEng;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescEng() {
        return descEng;
    }

    /**
     * 所有的政策类型
     */
    private static List<String> result = new ArrayList<>();
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    static {
        for (PolicyTypeEnum e : PolicyTypeEnum.values()) {
            result.add(e.getDesc());
            Map<String, String> map = new HashMap<>(8);
            map.put("desc", e.getDesc());
            map.put("descEng", e.getDescEng());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有政策类型
     *
     * @return
     */
    public static List<String> getPolicyType() {
        return result;
    }

    /**
     * 获取H5所需的所有政策类型
     *
     * @return
     */
    public static List<Map<String, String>> getPolicyTypeAll() {
        return resultAll;
    }

    /**
     * 根据中文政策类型desc获取其对应的英文描述
     *
     * @param desc
     * @return
     */
    public static String getDescEngByDesc(String desc) {
        if (StringUtils.isNotBlank(desc)) {
            for (PolicyTypeEnum e : PolicyTypeEnum.values()) {
                if (e.getDesc().equals(desc)) {
                    return e.getDescEng();
                }
            }
            return "";
        }
        return desc;
    }
}
