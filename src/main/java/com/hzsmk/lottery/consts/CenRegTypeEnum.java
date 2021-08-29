package com.hzsmk.lottery.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 户籍
 *
 * @author jiangjh
 * @date 2020/5/18 16:00
 */
public enum CenRegTypeEnum {

    SHI_QU("1", "市区常住居民户口"),
    WAIJI_JLZ("2", "属外籍人士，持永久居留证"),
    WAIJI_ZHEJIANG_JLZ("3", "属外籍人士，持浙江省海外高层次人才居留证"),
    BEIJING("4", "北京常住居民户口"),
    SHANGHAI("5", "上海常住居民户口"),
    GUANGZHOU("6", "广州常住居民户口"),
    SHENZHEN("7", "深圳常住居民户口"),
    XIAOSHAN("8", "萧山区常住居民户口"),
    YUHANG("9", "余杭区常住居民户口"),
    FUYANG("10", "富阳区常住居民户口"),
    JIANDE("11", "建德市常住居民户口"),
    LINAN("12", "临安区常住居民户口"),
    TONGLU("13", "桐庐县常住居民户口"),
    CHUNAN("14", "淳安县常住居民户口");

    private String type;
    private String typeDesc;

    CenRegTypeEnum(String type, String typeDesc) {
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
     * 所有的户籍类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (CenRegTypeEnum e : CenRegTypeEnum.values()) {
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
            for (CenRegTypeEnum e : CenRegTypeEnum.values()) {
                if (e.getType().equals(code)) {
                    return e.getTypeDesc();
                }
            }
        }
        return "";
    }
}
