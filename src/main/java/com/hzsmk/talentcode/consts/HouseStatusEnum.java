package com.hzsmk.talentcode.consts;

import org.apache.commons.lang3.StringUtils;

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
public enum HouseStatusEnum {


    WAITVALID("waitvalid", "待审核"),
    VALIDFAIL("validfail", "审核失败"),
    VALIDSUCC("validsucc", "审核成功"),
    ABANDON("abandon", "作废"),

    GRANTSUCC("grantsucc", "发放成功"),
    GRANTFAIL("grantfail", "发放失败"),
    GRANTING("granting", "发放中"),

    WAITAPPEAL("waitappeal", "待申诉");


    private String type;
    private String typeDesc;

    HouseStatusEnum(String type, String typeDesc) {
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
        for (HouseStatusEnum e : HouseStatusEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("type", e.getType());
            map.put("typeDesc", e.getTypeDesc());
            resultAll.add(map);
        }
    }

    /**
     * @return
     */
    public static List<Map<String, String>> getResultAll() {
        return resultAll;
    }

    /**
     * 根据code获取对应desc
     *
     * @return
     */
    public static String getDescByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (HouseStatusEnum e : HouseStatusEnum.values()) {
                if (e.getType().equals(code)) {
                    return e.getTypeDesc();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        List<Map<String, String>> resultAll = HouseStatusEnum.getResultAll();
        System.out.println(resultAll);
        System.out.println(HouseStatusEnum.GRANTING.getType());
    }
}
