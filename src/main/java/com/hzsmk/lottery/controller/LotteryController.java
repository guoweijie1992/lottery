package com.hzsmk.lottery.controller;

import com.hzsmk.common.base.RestResponse;
import com.hzsmk.lottery.reqIn.GetActInfoIn;
import com.hzsmk.lottery.reqIn.GetActUserInfoIn;
import com.hzsmk.lottery.reqIn.GetCodeIn;
import com.hzsmk.lottery.service.LotteryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RestResponse getActInfo(@RequestBody GetActInfoIn param){
        return lotteryService.getActInfo(param);
    }

    /**
     * 获取用户参与详情
     * @param param
     * @return
     */
    @RequestMapping("lottery/actUserInfo")
    public RestResponse getActUserInfo(@RequestBody GetActUserInfoIn param){
        return lotteryService.getActUserInfo(param);
    }


    /**
     * 立即抽奖获取券码
     * @param param
     * @return
     */
    @RequestMapping("lottery/getCode")
    public RestResponse getCode(@RequestBody GetCodeIn param){
        return lotteryService.getCode(param);
    }
}
