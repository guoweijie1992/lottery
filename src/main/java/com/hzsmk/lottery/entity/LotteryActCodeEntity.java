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
@TableName("lottery_act_code")
public class LotteryActCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 活动主键id
	 */
	private Long actId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 券码
	 */
	private String lotteryCode;
	/**
	 * 开奖状态 unplayed 未开始/playing 进行中/awaiting 待开奖/completed 已开奖
	 */
	private String lotteryStatus;
	/**
	 * 中奖状态 win已中奖 lose未中奖
	 */
	private String prizeStatus;
	/**
	 * 奖品主键id
	 */
	private Long prizeId;
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
	 * 是否已提示 0未提示 1已提示
	 */
	private Integer ifNotice;
	/**
	 * 券码类型  0自抽  1助力
	 */
	private Integer codeType;
	/**
	 * 是否首次参加 0 非首次 1首次
	 */
	private Integer ifFirstJoin;
	/**
	 * 同步优惠券系统状态  00未同步 01已同步
	 */
	private Integer syncStatus;
	/**
	 * 同步次数  超过5次不再同步
	 */
	private Integer syncCounts;

	public LotteryActCodeEntity(Long actId, String userId, String mobile, String lotteryCode, String lotteryStatus, Date createTime,
								Integer ifDelete, Integer codeType, Integer ifFirstJoin) {
		this.actId = actId;
		this.userId = userId;
		this.mobile = mobile;
		this.lotteryCode = lotteryCode;
		this.lotteryStatus = lotteryStatus;
		this.createTime = createTime;
		this.ifDelete = ifDelete;
		this.codeType = codeType;
		this.ifFirstJoin = ifFirstJoin;
	}
}
