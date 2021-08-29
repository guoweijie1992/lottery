package com.hzsmk.lottery.service.impl;

import com.hzsmk.common.base.RestResponse;
import com.hzsmk.lottery.reqIn.GetActInfoIn;
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

        return  RestResponse.success();
    }
}
