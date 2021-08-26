package com.hzsmk.talentcode.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户人才等级
 *
 * @author jiangjh
 * @date 2020/4/24 15:13
 */
public enum UserLevelEnum {

    A("A", "A类人才"),
    B("B", "B类人才"),
    C("C", "C类人才"),
    D("D", "D类人才"),
    E("E", "E类人才"),
    ZA("Z-A", "Z-A类人才"),
    ZB("Z-B", "Z-B类人才"),
    ZC("Z-C", "Z-C类人才"),
    ZD("Z-D", "Z-D类人才"),
    ZE("Z-E", "Z-E类人才"),

    //对接省人才码-等级设定
    ZJSWA("ZJSW_A", "ZJSW_A类人才"),
    ZJSWB("ZJSW_B", "ZJSW_B类人才"),
    ZJSWC("ZJSW_C", "ZJSW_C类人才"),
    ZJNA("ZJN_A", "ZJN_A类人才"),
    ZJNB("ZJN_B", "ZJN_B类人才"),
    ZJNC("ZJN_C", "ZJN_C类人才");

    private String level;
    private String desc;

    UserLevelEnum(String level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public String getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 所有的人才类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    static {
        for (UserLevelEnum e : UserLevelEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("level", e.getLevel());
            map.put("desc", e.getDesc());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有人才等级
     *
     * @return
     */
    public static List<Map<String, String>> getUserLevel() {
        return resultAll;
    }
}
