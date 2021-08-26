package com.hzsmk.talentcode.consts.integral;

public enum IntegralAccountStatusEnum {
    status_on(1, "启用"),
    status_off(2, "拉黑");

    private Integer status;
    private String desc;

    IntegralAccountStatusEnum(Integer status, String desc) {
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
