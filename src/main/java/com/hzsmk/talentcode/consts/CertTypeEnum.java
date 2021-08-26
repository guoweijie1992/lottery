package com.hzsmk.talentcode.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证件类型
 *
 * @author jiangjh
 * @date 2020/5/18 16:00
 */
public enum CertTypeEnum {

    IDCARD("2000", "身份证"),
    HUKOU("2001", "户口本"),
    PASSPORT("2002", "护照"),
    OFFICER("2003", "军官证"),
    SOLDIERS("2004", "士兵证"),
    HONGKONG("2005", "港澳居民来往内地通行证"),
    TAIWAN("2006", "台湾同胞来往内地通行证"),
    TEMP_IDCARD("2007", "临时身份证"),
    FOREIGNER("2008", "外国人居留证"),
    POLICE("2009", "警官证"),
    GAT_IDCARD("2010", "港澳台身份证"),
    RED_CARD("2011", "红卡"),
    DRIVER_CARD("2012", "驾驶证"),
    OTHER_CARD("2015", "其他个人证件"),
    LAWYER("2016", "律师证"),
    HUI_XIANG("2017", "回乡证"),
    CHINA_RESIDENCE("2020", "中华人民共和国永久居留证"),
    FOREIGN_EXPERT("2021", "外国专家证"),
    FOREIGN_WORK("2022", "外国人就业证"),
    GAT_WORK("2023", "台港澳人员就业证");

    private String type;
    private String typeDesc;

    CertTypeEnum(String type, String typeDesc) {
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
     * 所有的类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (CertTypeEnum e : CertTypeEnum.values()) {
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
            for (CertTypeEnum e : CertTypeEnum.values()) {
                if (e.getType().equals(code)) {
                    return e.getTypeDesc();
                }
            }
        }
        return "";
    }
}
