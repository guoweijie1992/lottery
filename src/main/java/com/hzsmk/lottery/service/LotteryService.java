package com.hzsmk.lottery.service;

import com.hzsmk.common.base.RestResponse;
import com.hzsmk.lottery.reqIn.GetActInfoIn;
import com.hzsmk.lottery.reqIn.GetActUserInfoIn;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:22
 * @Description:
 */

public interface LotteryService {
    RestResponse getActInfo(GetActInfoIn param);

    RestResponse actUserInfo(GetActUserInfoIn param);
}
