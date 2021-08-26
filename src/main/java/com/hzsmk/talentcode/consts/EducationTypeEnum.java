package com.hzsmk.talentcode.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人才学历类别
 *
 */
public enum EducationTypeEnum {

    BACHELOR("本科", "bachelor"),
    MASTER("硕士", "master"),
    DOCTOR("博士", "doctor"),
    OTHER("其他", "other");


    private String type;
    private String typeEng;

    EducationTypeEnum(String type, String typeEng) {
        this.type = type;
        this.typeEng = typeEng;
    }

    public String getType() {
        return type;
    }

    public String getTypeEng() {
        return typeEng;
    }

    /**
     * 所有人才学历类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (EducationTypeEnum e : EducationTypeEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("type", e.getType());
            map.put("typeEng", e.getTypeEng());
            resultAll.add(map);
        }
    }

    /**
     * 获取人才学历类型
     *
     * @return
     */
    public static List<Map<String, String>> getBankType() {
        return resultAll;
    }
}
