package com.hzsmk.lottery.req.in;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:53
 * @Description:
 */
@Data
public class HelpIn {

    @NotNull(message = "活动id不能为空")
    private  Long actId;

    @NotEmpty(message = "accessToken不能为空")
    private  String accessToken;

    @NotEmpty(message = "mobile不能为空")
    private  String mobile;


}

