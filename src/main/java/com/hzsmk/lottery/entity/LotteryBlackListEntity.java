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
@TableName("lottery_black_list")
public class LotteryBlackListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 参与活动状态  0不可参与 1可参与
	 */
	private Integer status;
	/**
	 * 活动id
	 */
	private Long actId;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;

}
