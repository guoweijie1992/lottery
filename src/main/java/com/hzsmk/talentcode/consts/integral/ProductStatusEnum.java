package com.hzsmk.talentcode.consts.integral;

public enum ProductStatusEnum {
    on("已上架"),
    down("下架");

    private String desc;

    ProductStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
