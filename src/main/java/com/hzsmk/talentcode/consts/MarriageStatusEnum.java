package com.hzsmk.talentcode.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 婚姻状态
 *
 * @author jiangjh
 * @date 2020/5/19 10:36
 */
public enum MarriageStatusEnum {

    UNMARRIED("1001", "未婚"),
    MARRIED("1002", "已婚"),
    DIVORCE("1003", "离异"),
    WIDOWED("1004", "丧偶");

    private String type;
    private String typeDesc;

    MarriageStatusEnum(String type, String typeDesc) {
        this.type = type;
        this.typeDesc = typeDesc;
    }

    public String getType() {
        return type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * 所有的婚姻
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (MarriageStatusEnum e : MarriageStatusEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("type", e.getType());
            map.put("typeDesc", e.getTypeDesc());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有
     *
     * @return
     */
    public static List<Map<String, String>> getAllStatus() {
        return resultAll;
    }

    /**
     * 根据code获取对应desc
     *
     * @return
     */
    public static String getDescByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (MarriageStatusEnum e : MarriageStatusEnum.values()) {
                if (e.getType().equals(code)) {
                    return e.getTypeDesc();
                }
            }
        }
        return "";
    }
}
