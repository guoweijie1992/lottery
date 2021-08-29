package com.hzsmk.lottery.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车牌竞价补贴状态
 *
 * @author jiangjh
 * @date 2020/4/24 15:13
 */
public enum CarStatusEnum {

    UNCHECK("00", "审核中"),
    PASS("10", "审核成功,待打款"),
    PAY_ING("11", "打款处理中"),
    PAY_SUC("12", "打款成功"),
    PAY_ERR("13", "打款失败"),
    REJECT("20", "已驳回"),
    REFUSE("30", "已拒绝");

    private String status;
    private String desc;

    CarStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 所有的状态
     */
    private static List<Map<String, String>> resultAll = new ArrayList<>();

    static {
        for (CarStatusEnum e : CarStatusEnum.values()) {
            Map<String, String> map = new HashMap<>(8);
            map.put("status", e.getStatus());
            map.put("desc", e.getDesc());
            resultAll.add(map);
        }
    }

    /**
     * 获取所有状态
     *
     * @return
     */
    public static List<Map<String, String>> getCarStatus() {
        return resultAll;
    }
}
