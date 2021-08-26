package com.hzsmk.talentcode.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 金融产品类别
 *
 * @author jiangjh
 * @date 2020/4/22 20:16
 */
public enum BankTypeEnum {

    PERSONAL("个人业务", "personal"),
    ENTERPRISE("企业业务", "enterprise"),
    INCREMENT_SERVE("增值服务", "increment_serve"),
    ESPECIAL_SERVE("特色服务", "especial_serve");

    private String type;
    private String typeEng;

    BankTypeEnum(String type, String typeEng) {
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
     * 所有的金融产品类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (BankTypeEnum e : BankTypeEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("type", e.getType());
            map.put("typeEng", e.getTypeEng());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有金融产品类型
     *
     * @return
     */
    public static List<Map<String, String>> getBankType() {
        return resultAll;
    }
}
