package com.hzsmk.talentcode.consts.integral;

public enum BannerStatusEnum {
    down("下架"),
    up("上架");
    private String desc;

    BannerStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
