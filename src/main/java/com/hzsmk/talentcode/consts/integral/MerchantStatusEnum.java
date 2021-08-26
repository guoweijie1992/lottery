package com.hzsmk.talentcode.consts.integral;

/**
 * 商户状态
 */
public enum MerchantStatusEnum {
    disable("禁用"),
    enable("启用"),
    delete("删除");

    MerchantStatusEnum(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
