package com.hzsmk.talentcode.consts.integral;

public enum IntegralAccountRecordChangeTypeEnum {
    status_increase(1, "增加"),
    status_reduce(2, "扣除");
    private Integer status;
    private String desc;

    IntegralAccountRecordChangeTypeEnum(Integer status, String desc) {
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
