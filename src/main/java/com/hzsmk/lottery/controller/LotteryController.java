package com.hzsmk.lottery.controller;

import com.hzsmk.common.base.RestResponse;
import com.hzsmk.lottery.req.in.*;
import com.hzsmk.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:21
 * @Description:
 */
@RestController
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    /**
     * 根据id获取活动详情
     * @param param
     * @return
     */
    @RequestMapping("lottery/getActInfo")
    public RestResponse getActInfo(@Valid @RequestBody GetActInfoIn param){
        return lotteryService.getActInfo(param);
    }

    /**
     * 获取用户参与详情
     * @param param
     * @return
     */
    @RequestMapping("lottery/actUserInfo")
    public RestResponse getActUserInfo(@Valid @RequestBody GetActUserInfoIn param){
        return lotteryService.getActUserInfo(param);
    }


    /**
     * 用户自己抽奖获取券码
     * @param param
     * @return
     */
    @RequestMapping("lottery/getCode")
    public RestResponse getCode(@Valid @RequestBody GetCodeIn param){
        return lotteryService.getCode(param);
    }


    /**
     * 获取助力页面详情
     * @param param
     * @return
     */
    @RequestMapping("lottery/getHelpInfo")
    public RestResponse getHelpInfo(@Valid @RequestBody GetHelpInfoIn param){
        return lotteryService.getHelpInfo(param);
    }

    /**
     * 助力获取奖券
     * @param param
     * @return
     */
    @RequestMapping("lottery/help")
    public RestResponse help(@Valid @RequestBody HelpIn param){
        return lotteryService.help(param);
    }

    /**
     * 我的奖品
     * @param param
     * @return
     */
    @RequestMapping("lottery/getMyPrize")
    public RestResponse getMyPrize(@Valid @RequestBody GetMyPrizeIn param){
        return lotteryService.getMyPrize(param);
    }
}
