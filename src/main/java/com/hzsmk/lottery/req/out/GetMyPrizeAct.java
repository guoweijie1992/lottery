package com.hzsmk.lottery.req.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Guowj
 * @Classname GetMyPrizeAct
 * @Date2021 2021/8/31 0031 19:46
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetMyPrizeAct {

    //活动名称
    private String lotteryName;
    //活动主键ID
    private Long lotteryId;
    //奖品图片
    private String imgUrl;
    //使用链接
    private String useUrl;
    //活动描述
    private String description;
    //中奖状态
    private String prizeStatus;
    //是否提示中奖 y需要提示 n不需要提示
    private String  ifNotice;
    //奖品价格
    private Long   prizePrice;
    //中奖券码
    private String lotteryCode;
    //中奖券码ID
    private Long lotteryCodeId;

    //最大奖券数量
    private Integer maxCodeCounts;

    //活动结束时间
    private Date endTime;
    //奖品名称
    private String prizeName;

    //活动券码列表
    private List<String> actCodeList;



}
