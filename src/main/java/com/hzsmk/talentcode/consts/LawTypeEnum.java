package com.hzsmk.talentcode.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 法律援助类型枚举
 *
 * @author jiangjh
 * @date 2020/4/16 11:39
 */
public enum LawTypeEnum {

    CIVIL("民事", "civil"),
    BUSINESS("商事", "business"),
    ADMINISTRATIVE("行政", "administrative"),
    FOREIGN("涉外", "foreign"),
    CRIMINAL("刑事", "criminal");

    /**
     * 行政类别
     */
    private static String[] administrativeType = {"行政诉讼", "国家赔偿"};
    private static String[] administrativeTypeEng = {"行政诉讼", "国家赔偿"};

    /**
     * 刑事类别
     */
    private static String[] criminalType = {"刑事辩护"};
    private static String[] criminalTypeEng = {"刑事辩护"};

    /**
     * 民事类
     */
    private static String[] civilType = {"婚姻家庭", "工程建设",
            "抵押担保", "合同法", "民事相关", "劳动人事",
            "人身权", "房地产", "经济相关", "遗嘱继承"};
    private static String[] civilTypeEng = {"婚姻家庭", "工程建设",
            "抵押担保", "合同法", "民事相关", "劳动人事",
            "人身权", "房地产", "经济相关", "遗嘱继承"};

    /**
     * 商事类
     */
    private static String[] businessType = {"公司法", "知识产权", "银行", "风险投资",
            "海事海商", "证券", "股份转让", "反不正当竞争",
            "金融", "破产清算", "公司收购", "工商税务",
            "企业改制", "电子商务", "广告媒体", "高科技"};
    private static String[] businessTypeEng = {"公司法", "知识产权", "银行", "风险投资",
            "海事海商", "证券", "股份转让", "反不正当竞争",
            "金融", "破产清算", "公司收购", "工商税务",
            "企业改制", "电子商务", "广告媒体", "高科技"};

    /**
     * 涉外类
     */
    private static String[] foreignType = {"国际贸易", "涉外法律", "国际仲裁"};
    private static String[] foreignTypeEng = {"国际贸易", "涉外法律", "国际仲裁"};

    /**
     * 所有类别
     */
    private static List<Map<String, Object>> result = new ArrayList<>();

    static {
        // 民事类
        Map<String, Object> civilMap = new HashMap<>();
        civilMap.put("desc", CIVIL.getDesc());
        civilMap.put("descEng", CIVIL.getDescEng());
        List<Map<String, String>> civilTypes = new ArrayList<>();
        for (int i = 0, size = civilType.length; i < size; i++) {
            Map<String, String> m = new HashMap<>();
            m.put("desc", civilType[i]);
            m.put("descEng", civilTypeEng[i]);

            civilTypes.add(m);
        }
        civilMap.put("types", civilTypes);
        result.add(civilMap);

        // 商事类
        Map<String, Object> businessMap = new HashMap<>();
        businessMap.put("desc", BUSINESS.getDesc());
        businessMap.put("descEng", BUSINESS.getDescEng());
        List<Map<String, String>> businessTypes = new ArrayList<>();
        for (int i = 0, size = businessType.length; i < size; i++) {
            Map<String, String> m = new HashMap<>();
            m.put("desc", businessType[i]);
            m.put("descEng", businessTypeEng[i]);

            businessTypes.add(m);
        }
        businessMap.put("types", businessTypes);
        result.add(businessMap);

        // 行政
        Map<String, Object> administrativeMap = new HashMap<>();
        administrativeMap.put("desc", ADMINISTRATIVE.getDesc());
        administrativeMap.put("descEng", ADMINISTRATIVE.getDescEng());
        List<Map<String, String>> administrativeTypes = new ArrayList<>();
        for (int i = 0, size = administrativeType.length; i < size; i++) {
            Map<String, String> m = new HashMap<>();
            m.put("desc", administrativeType[i]);
            m.put("descEng", administrativeTypeEng[i]);

            administrativeTypes.add(m);
        }
        administrativeMap.put("types", administrativeTypes);
        result.add(administrativeMap);

        // 涉外
        Map<String, Object> foreignMap = new HashMap<>();
        foreignMap.put("desc", FOREIGN.getDesc());
        foreignMap.put("descEng", FOREIGN.getDescEng());
        List<Map<String, String>> foreignTypes = new ArrayList<>();
        for (int i = 0, size = foreignType.length; i < size; i++) {
            Map<String, String> m = new HashMap<>();
            m.put("desc", foreignType[i]);
            m.put("descEng", foreignTypeEng[i]);

            foreignTypes.add(m);
        }
        foreignMap.put("types", foreignTypes);
        result.add(foreignMap);

        // 刑事
        Map<String, Object> criminalMap = new HashMap<>();
        criminalMap.put("desc", CRIMINAL.getDesc());
        criminalMap.put("descEng", CRIMINAL.getDescEng());
        List<Map<String, String>> criminalTypes = new ArrayList<>();
        for (int i = 0, size = criminalType.length; i < size; i++) {
            Map<String, String> m = new HashMap<>();
            m.put("desc", criminalType[i]);
            m.put("descEng", criminalTypeEng[i]);

            criminalTypes.add(m);
        }
        criminalMap.put("types", criminalTypes);
        result.add(criminalMap);
    }

    /**
     * 第一分类
     */
    private String desc;
    private String descEng;

    LawTypeEnum(String desc, String descEng) {
        this.desc = desc;
        this.descEng = descEng;
    }

    /**
     * 获取所有类别
     *
     * @return
     */
    public static List<Map<String, Object>> getAllLawType() {
        return result;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescEng() {
        return descEng;
    }
}
