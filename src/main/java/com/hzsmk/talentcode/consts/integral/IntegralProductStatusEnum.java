package com.hzsmk.talentcode.consts.integral;

public enum IntegralProductStatusEnum {
    status_open(1, "立即上架"),

    status_out(2, "草稿状态或下架状态");

    private Integer status;
    private String desc;

    IntegralProductStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
