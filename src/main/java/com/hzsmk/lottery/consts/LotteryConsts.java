package com.hzsmk.lottery.consts;

/**
 * @author Guowj
 * @Classname LotteryConsts
 * @Date2021 2021/8/30 0030 20:55
 */
public final class LotteryConsts {

    /**
     * 抽奖频次 天
     */
    public static final String DRAWFREQUENCY_DAY="day";
    /**
     * 抽奖频次 周
     */
    public static final String DRAWFREQUENCY_WEEK="week";
    /**
     * 抽奖频次 活动期间
     */
    public static final String DRAWFREQUENCY_AROUND="around";

    /**
     * 码类型 自抽
     */
    public static final Integer CODETYPE_SELF=0;

    /**
     * 码类型 助力
     */
    public static final Integer CODETYPE_HELP=1;

    /**
     * 抽奖状态 unplayed 未开始/playing 进行中/awaiting 待开奖/completed 已开奖
     */
    public static final String LOTTERY_STATUS_UNPLAYED="unplayed";
    public static final String LOTTERY_STATUS_PLAYING="playing";
    public static final String LOTTERY_STATUS_AWAITING="awaiting";
    public static final String LOTTERY_STATUS_COMPLETED="completed";


    /**
     * 中奖状态 win中奖/lose未中奖
     */
    public static final String PRIZE_STATUS_WIN="win";
    public static final String PRIZE_STATUS_LOSE="lose";

    /**
     * 是否首次参与  0非首次 1首次
     */
    public static final Integer FIRST_JOIN_Y=1;
    public static final Integer FIRST_JOIN_N=0;


    /**
     * 是否删除 0 否 1 是
     */
    public static final Integer IFDELETE_N=0;
    public static final Integer IFDELETE_Y=1;

    /**
     * 是否删除 0 否 1 是
     */
    public static final String PRIZE_TYPE_AWAITING="awaiting";
    public static final String PRIZE_TYPE_COMPLETED="completed";


}
