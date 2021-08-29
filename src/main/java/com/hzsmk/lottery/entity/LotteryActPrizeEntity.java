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
@TableName("lottery_act_prize")
public class LotteryActPrizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 活动表主键id
	 */
	private Long actId;
	/**
	 * 奖品表主键id
	 */
	private Long prizeId;
	/**
	 * 奖品等级描述
	 */
	private String prizeLevelDesc;
	/**
	 * 奖品数量
	 */
	private Integer prizeNumber;
	/**
	 * 使用链接
	 */
	private String useUrl;
	/**
	 * 优惠券编号
	 */
	private String couponNumber;
	/**
	 * 奖品优先级
	 */
	private Integer sort;
	/**
	 * 奖品状态  0不可用 1可用 
	 */
	private String status;
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
	 * 活动奖品描述，我的奖品页面展示
	 */
	private String description;

}
