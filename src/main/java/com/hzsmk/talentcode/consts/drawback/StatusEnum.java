package com.hzsmk.talentcode.consts.drawback;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退税申请状态
 *
 * @author shenyx
 * @date 2020/11/27 19:30
 */
public enum StatusEnum {

    STATUS_PENDING("1", "待审核"),
    STATUS_REJECTED("2", "驳回"),
    STATUS_SUCCEED("3", "通过");

    private String type;
    private String typeDesc;

    StatusEnum(String type, String typeDesc) {
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
        for (StatusEnum e : StatusEnum.values()) {
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
            for (StatusEnum e : StatusEnum.values()) {
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
            for (StatusEnum e : StatusEnum.values()) {
                if (e.getTypeDesc().equals(desc)) {
                    return e.getType();
                }
            }
        }
        return "";
    }
}
