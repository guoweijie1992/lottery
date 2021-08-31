package com.hzsmk.lottery.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 助力记录表
 * 
 * @author guowj
 * @email 18698577236@163.com
 * @date 2021-08-29 21:41:05
 */
@Data
@TableName("lottery_help_record")
public class LotteryHelpRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private String helpUserId;
	/**
	 * 手机号
	 */
	private String helpMobile;
	/**
	 * 手机号
	 */
	private String helpUserImg;
	/**
	 * 卷码表id
	 */
	private Long codeId;
	/**
	 * 活动ID
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

	public LotteryHelpRecordEntity(String helpUserId, String helpMobile, String helpUserImg, Long codeId, Date createTime,Long actId) {
		this.helpUserId = helpUserId;
		this.helpMobile = helpMobile;
		this.helpUserImg = helpUserImg;
		this.codeId = codeId;
		this.createTime = createTime;
		this.actId = actId;
	}

	public LotteryHelpRecordEntity() {
	}
}
