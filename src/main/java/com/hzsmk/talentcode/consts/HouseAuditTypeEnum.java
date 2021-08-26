package com.hzsmk.talentcode.consts;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租房审核类型
 * <p>
 * hclx（核查类型） jgms（结果描述）（数组）
 * 1 婚姻核查结果不符合申请人所填写情况
 * 2 未查询到有效的租赁备案记录
 * 3 社保或法人不符合
 * 4 正在享受其他住房优惠政策
 * 5 房产情况不符合
 * 6 人才认定不符合
 * 7 资格证已满5年
 *
 * @author jiangjh
 * @date 2020/5/27 15:56
 */
public enum HouseAuditTypeEnum {

    MARRY_ERROR("1", "婚姻核查结果不符合申请人所填写情况"),
    NO_RECORD("2", "未查询到有效的租赁备案记录"),
    SOCIAL_INSURANCE_ERROR("3", "社保或法人不符合"),
    HAS_OTHER("4", "正在享受其他住房优惠政策"),
    HOUSE_ERRORR("5", "房产情况不符合"),
    TALENT_USER_ERROR("6", "人才认定不符合"),
    HAVED_FIVE_YEAR("7", "资格证已满5年");

    private String type;
    private String typeDesc;

    HouseAuditTypeEnum(String type, String typeDesc) {
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
     * 所有的类型
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    /**
     *
     */
    static {
        for (HouseAuditTypeEnum e : HouseAuditTypeEnum.values()) {
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
            for (HouseAuditTypeEnum e : HouseAuditTypeEnum.values()) {
                if (e.getType().equals(code)) {
                    return e.getTypeDesc();
                }
            }
        }
        return "";
    }
}
