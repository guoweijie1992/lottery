package com.hzsmk.talentcode.consts.integral;

public enum ProductStockStatusEnum {
    on("上架"),

    down("下架");

    private String desc;

    ProductStockStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
