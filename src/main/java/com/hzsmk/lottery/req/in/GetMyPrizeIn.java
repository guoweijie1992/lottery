package com.hzsmk.lottery.req.in;

import com.hzsmk.lottery.consts.LotteryConsts;
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
public class GetMyPrizeIn {

    @NotEmpty(message = "accessToken不能为空")
    private  String accessToken;

    @NotBlank(message = "type不能为空")
    @Pattern(regexp = "awaiting|completed", message = "奖品类型错误")
    private String type;


}

