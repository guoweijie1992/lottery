package com.hzsmk.lottery.consts;

import org.apache.commons.lang3.StringUtils;

/**
 * 区域
 *
 * @author jiangjh
 * @date 2020/4/24 15:13
 */
public enum TalentMechantTypeEnum {

    Hangzhou("c","杭州市"),
    Province("p","省属"),
    Fuyang("fy","富阳"),
    Tonglu("tl","桐庐"),
    Lingan("la","临安"),
	Jiangde("dj","建德"),
	QiangTang("qt","钱塘");

    private String code;
    
	private String desc;

    TalentMechantTypeEnum(String code, String desc) {
    	this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

   

    /**
     * 根据code获取对应desc
     *
     * @return
     */
    public static String getDescByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (TalentMechantTypeEnum e : TalentMechantTypeEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e.getDesc();
                }
            }
        }
        return "";
    }
}
