package com.hzsmk.lottery.reqIn;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:53
 * @Description:
 */
@Data
public class GetActUserInfoIn {

    @NotEmpty(message = "活动id不能为空")
    private  Long actId;
}
