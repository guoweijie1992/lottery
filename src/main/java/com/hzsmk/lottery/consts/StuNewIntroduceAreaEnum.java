package com.hzsmk.lottery.consts;

/**
 * @author wangsy
 * @Date: 2021-04-23 10:48
 */
public enum StuNewIntroduceAreaEnum {

    市本级(1 ,"市本级"),
    上城区(2 ,"杭州市上城区"),
    下城区(3 ,"杭州市下城区"),
    江干区(4 ,"杭州市江干区"),
    拱墅区(5 ,"杭州市拱墅区"),
    西湖区(6, "杭州市西湖区"),
    滨江区(7 ,"杭州市滨江区"),
    萧山区(8 ,"杭州市萧山区"),
    余杭区(9,"杭州市余杭区"),
    富阳区(10 ,"杭州市富阳区"),
    临安区(11 ,"杭州市临安区"),
    桐庐县(12 ,"杭州市桐庐县"),
    淳安县(13 ,"杭州市淳安县"),
    西湖风景名胜区(14 ,"西湖风景名胜区"),
    钱塘新区(15 ,"杭州市钱塘新区"),
    建德市(16 ,"杭州市建德市"),
    合计(0 ,"合计");

    private Integer areaCode;
    private String desc;

    StuNewIntroduceAreaEnum(Integer areaCode, String desc) {
        this.areaCode = areaCode;
        this.desc = desc;

    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据code获取对应desc
     *
     * @return
     */
    public static String getDescByCode(Integer areaCode) {
        if (areaCode != null) {
            for (StuNewIntroduceAreaEnum e : StuNewIntroduceAreaEnum.values()) {
                if (e.getAreaCode().equals(areaCode)) {
                    return e.getDesc();
                }
            }
        }
        return "";
    }
}
