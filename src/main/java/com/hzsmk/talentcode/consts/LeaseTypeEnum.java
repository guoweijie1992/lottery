package com.hzsmk.talentcode.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 众创租赁类型枚举
 *
 * @author jiangjh
 * @date 2020/4/23 14:52
 */
public enum LeaseTypeEnum {


    ECONOMICS("数字经济核心产业", "Economics"),
    LIFE("生命健康产业", "life"),
    MANUFACTURE("高端装备产业（制造业）", "Manufacture"),
//    CULTURE("文化产业", "Culture"),
//    EDUCATION("教育产业", "education"),
//    ENVIRONMENTAL_PROTECTION("环保产业", "environmental protection"),
//    NEW_ENERGY("新能源产业", "new energy"),
//    FINANCE("金融产业", "Finance"),
    OTHER("其他产业", "other");

    private String type;
    private String typeEng;

    LeaseTypeEnum(String type, String typeEng) {
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
     * 所有的众创租赁类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    static {
        for (LeaseTypeEnum e : LeaseTypeEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("type", e.getType());
            map.put("typeEng", e.getTypeEng());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有众创租赁类型
     *
     * @return
     */
    public static List<Map<String, String>> getLeaseType() {
        return resultAll;
    }
}
