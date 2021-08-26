package com.hzsmk.talentcode.consts.drawback;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 校验单位是否在杭
 *
 * @author shenyx
 * @date 2020/11/27 19:30
 */
public enum VerifyEnum {

    VERIFY_IN_HANGZHOU("1", "在杭"),
    VERIFY_NOT_IN_HANGZHOU("2", "非杭"),
    VERIFY_NO("3", "未校验");

    private String type;
    private String typeDesc;

    VerifyEnum(String type, String typeDesc) {
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
        for (VerifyEnum e : VerifyEnum.values()) {
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
            for (VerifyEnum e : VerifyEnum.values()) {
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
            for (VerifyEnum e : VerifyEnum.values()) {
                if (e.getTypeDesc().equals(desc)) {
                    return e.getType();
                }
            }
        }
        return "";
    }
}
