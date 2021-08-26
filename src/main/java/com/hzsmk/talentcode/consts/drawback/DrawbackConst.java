package com.hzsmk.talentcode.consts.drawback;

import java.util.Arrays;
import java.util.List;

/**
 * 退税申请常量
 *
 * @author shenyx
 * @date 2020/11/24 19:30
 */
public class DrawbackConst {

    /**
     * 申请方式（1：在线；2：窗口）
     */
    public static final Integer APPLICATION_METHOD_ONLINE = 1;
    public static final Integer APPLICATION_METHOD_WINDOW = 2;

    /**
     * 人才类别（1：境内人才；2：台港澳⼈才；3：外籍人才）
     */
    public static final Integer TYPE_DOMESTIC = 1;
    public static final Integer TYPE_HMT = 2;
    public static final Integer TYPE_FOREIGN = 3;

    /**
     * 获取所有境内人才证件类型
     *
     * @return
     */
    public static List<String> getDomestic() {
        List<String> domestic = Arrays.asList("中华人民共和国居民身份证","军官证");
        return domestic;
    }

    /**
     * 获取所有台港澳⼈才证件类型
     *
     * @return
     */
    public static List<String> getHmt() {
        List<String> hmt = Arrays.asList("台港澳人员就业证","港澳台居民居住证或通行证");
        return hmt;
    }

    /**
     * 是否为相关领导（0：否；1：是）
     */
    public static final Integer LEADER_NO = 0;
    public static final Integer LEADER_YES = 1;

    /**
     * 审核状态（0：暂无申请记录；1：待审核；2：驳回；3：发放成功）
     */
    public static final Integer STATUS_NULL = 0;
    public static final Integer STATUS_PENDING = 1;
    public static final Integer STATUS_REJECTED = 2;
    public static final Integer STATUS_SUCCEED = 3;

    /**
     * 校验（1：在杭；2：非杭；3：未校验）
     */
    public static final Integer VERIFY_IN_HANGZHOU = 1;
    public static final Integer VERIFY_NOT_IN_HANGZHOU = 2;
    public static final Integer VERIFY_NO = 3;
}
