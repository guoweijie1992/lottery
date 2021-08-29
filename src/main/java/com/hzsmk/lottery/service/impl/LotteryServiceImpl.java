package com.hzsmk.lottery.service.impl;

import com.hzsmk.common.base.RestResponse;
import com.hzsmk.lottery.reqIn.GetActInfoIn;
import com.hzsmk.lottery.reqIn.GetActUserInfoIn;
import com.hzsmk.lottery.service.LotteryService;
import org.springframework.stereotype.Service;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:22
 * @Description:
 */
@Service
public class LotteryServiceImpl implements LotteryService {
    @Override
    public RestResponse getActInfo(GetActInfoIn param) {

        /**
         * todo
         * 1.根据id获取活动
         * 2.判断活动是否存在，状态是否正确
         * 3.返回活动
         */
        return  RestResponse.success();
    }

    @Override
    public RestResponse actUserInfo(GetActUserInfoIn param) {
        /**
         * todo
         * 1.获取活动id关联查询对应得用户参与记录
         * 2.获取
         */
        return null;
    }
}
