package com.hzsmk.talentcode.consts.integral;

public enum ProductStockGrantEnum {
    grant("已下发"),

    no("未下发");

    private String desc;

    ProductStockGrantEnum(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
}
