package com.hzsmk.lottery.req.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:53
 * @Description:
 */
@Data
public class NotifiedIn {

    @NotNull(message = "lotteryCodeId不能为空")
    private  Long lotteryCodeId;

}

