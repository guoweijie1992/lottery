package com.hzsmk.lottery.consts;

/**
 * 机场管理核销白名单状态
 */
public enum AirportEnum {
    status_reserved(1, "已预约"),
    status_written_off(2, "已核销"),
    status_overdue(3, "已过期");

    private Integer status;
    private String desc;

    AirportEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }
}
