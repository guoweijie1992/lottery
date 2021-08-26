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
public enum TypeEnum {

    TYPE_DOMESTIC("1", "境内人才"),
    TYPE_HMT("2", "台港澳人才"),
    TYPE_FOREIGN("3", "外籍人才");

    private String type;
    private String typeDesc;

    TypeEnum(String type, String typeDesc) {
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
     * 所有的户籍类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (TypeEnum e : TypeEnum.values()) {
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
            for (TypeEnum e : TypeEnum.values()) {
                if (e.getType().equals(code)) {
                    return e.getTypeDesc();
                }
            }
        }
        return "";
    }

    /**
     * 根据desc获取对应code
     *
     * @return
     */
    public static String getCodeByDesc(String desc) {
        if (StringUtils.isNotBlank(desc)) {
            for (TypeEnum e : TypeEnum.values()) {
                if (e.getTypeDesc().equals(desc)) {
                    return e.getType();
                }
            }
        }
        return "";
    }
}
