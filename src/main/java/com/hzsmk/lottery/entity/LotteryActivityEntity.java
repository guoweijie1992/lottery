package com.hzsmk.lottery.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author guowj
 * @email 18698577236@163.com
 * @date 2021-08-29 21:41:05
 */
@Data
@TableName("lottery_activity")
public class LotteryActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 活动名称
	 */
	private String lotteryName;
	/**
	 * 活动开始时间
	 */
	private Date startTime;
	/**
	 * 活动结束时间
	 */
	private Date endTime;
	/**
	 * 开奖时间
	 */
	private Date drawTime;
	/**
	 * 活动状态  0不可用 1可用 
	 */
	private String status;
	/**
	 * 开奖状态 unplayed 未开始/playing 进行中/awaiting 待开奖/completed 已开奖
	 */
	private String lotteryStatus;
	/**
	 * 最大被助力次数
	 */
	private Integer maxBeHelp;
	/**
	 * 最大为他人助力次数
	 */
	private Integer maxHelp;
	/**
	 * 最大奖券数量
	 */
	private Integer maxCodeCounts;
	/**
	 * 活动规则链接
	 */
	private String actRule;
	/**
	 * 抽奖码页面提示
	 */
	private String tips;
	/**
	 * 活动描述
	 */
	private String description;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 是否删除 0未删除 1已删除 默认0
	 */
	private Integer ifDelete;
	/**
	 * 主标题
	 */
	private String title;
	/**
	 * 子标题
	 */
	private String subTitle;
	/**
	 * 抽奖次数
	 */
	private Integer drawCounts;
	/**
	 * 抽奖频次  day 天/week 星期 /around 次
	 */
	private String drawFrequency;
	/**
	 * 分享页背景图
	 */
	private String shareImgUrl;
	/**
	 * 背景图片
	 */
	private String bgImgUrl;
	/**
	 * 未开始图片
	 */
	private String unplayedImgUrl;
	/**
	 * 进行中图片
	 */
	private String playingImgUrl;
	/**
	 * 待开奖图片
	 */
	private String awaitingImgUrl;
	/**
	 * 已结束图片
	 */
	private String completedImgUrl;
	/**
	 * 按钮图片
	 */
	private String btnImgUrl;
	/**
	 * 埋点id
	 */
	private String businessId;

}
