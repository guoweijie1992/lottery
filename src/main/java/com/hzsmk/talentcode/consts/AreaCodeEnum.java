package com.hzsmk.talentcode.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 区域
 *
 * @author jiangjh
 * @date 2020/4/24 15:13
 */
public enum AreaCodeEnum {
    /**
     *
     */
    市属("330101", "市属", true),
    市属00("330000", "市属", true),
    杭州市("330100", "杭州市", true),
    上城区("330102", "上城", true),
    下城区("330103", "下城", true),
    江干区("330104", "江干", true),
    拱墅区("330105", "拱墅", true),
    西湖区("330106", "西湖", true),
    滨江区("330108", "滨江", true),
    钱塘新区("330139", "钱塘新区", true),
    钱塘新区2("330191", "钱塘新区", true),
    西湖风景名胜区("330142", "西湖风景名胜区", true),
    下沙经济开发区("330131", "下沙", true),
    钱江经济开发区("330132", "钱江经济", true),
    大江东产业集聚区("330133", "大江东", true),
    大江东产业集聚区税务局("330193", "大江东", true),
    萧山("330109", "萧山", false),
    余杭("330110", "余杭", false),
    富阳("330111", "富阳", false),
    临安("330112", "临安", false),
    桐庐("330122", "桐庐", false),
    建德("330182", "建德", false),
    淳安("330127", "淳安", false);

    private String areaCode;
    private String desc;
    private boolean match;

    AreaCodeEnum(String areaCode, String desc, boolean match) {
        this.areaCode = areaCode;
        this.desc = desc;
        this.match = match;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getMatch() {
        return match;
    }

    /**
     * 所有的区域code
     */
    private static List<String> resultAll = new ArrayList<>();
    private static List<String> matchAll = new ArrayList<>();
    private static String codeAll = "";

    private static List<Map<String, String>> allCode = new ArrayList<>();
    private static List<Map<String, String>> matchCode = new ArrayList<>();

    static {
        for (AreaCodeEnum e : AreaCodeEnum.values()) {
            resultAll.add(e.getAreaCode());
            Map<String, String> map = new HashMap<>();
            map.put("code", e.getAreaCode());
            map.put("desc", e.getDesc());
            allCode.add(map);
        }
    }

    static {
        for (AreaCodeEnum e : AreaCodeEnum.values()) {
            if (e.getMatch()) {
                matchAll.add(e.getAreaCode());
                Map<String, String> map = new HashMap<>();
                map.put("code", e.getAreaCode());
                map.put("desc", e.getDesc());
                matchCode.add(map);
            }
        }
    }

    static {

        StringBuffer buf = new StringBuffer();
        for (AreaCodeEnum e : AreaCodeEnum.values()) {
            buf.append(e.getAreaCode()).append(",");
        }
        codeAll = buf.substring(0,buf.length()-1);
    }

    /**
     * 获取所有的区域code
     *
     * @return
     */
    public static List<String> getResultAll() {
        return resultAll;
    }

    public static List<Map<String, String>> getAllCode() {
        return allCode;
    }

    public static List<String> getMatchAll() {
        return matchAll;
    }

    public static List<Map<String, String>> getMatchCode() {
        return matchCode;
    }

    public static String getCodeAll() {
        return codeAll;
    }

    /**
     * 根据code获取对应desc
     *
     * @return
     */
    public static String getDescByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (AreaCodeEnum e : AreaCodeEnum.values()) {
                if (e.getAreaCode().equals(code)) {
                    return e.getDesc();
                }
            }
        }
        return "";
    }
}
