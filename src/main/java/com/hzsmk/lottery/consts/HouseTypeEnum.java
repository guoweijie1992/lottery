package com.hzsmk.lottery.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租房补贴所有状态
 *
 * @author jiangjh
 * @date 2020/4/22 20:16
 */
public enum HouseTypeEnum {

    buy("238001", "购房补贴"),
    rent("238002", "租房补贴");

    private String type;
    private String typeDesc;

    HouseTypeEnum(String type, String typeDesc) {
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
     * 类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (HouseTypeEnum e : HouseTypeEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("type", e.getType());
            map.put("typeDesc", e.getTypeDesc());
            resultAll.add(map);
        }
    }

    /**
     *
     *
     * @return
     */
    public static List<Map<String, String>> getResultAll() {
        return resultAll;
    }
}
