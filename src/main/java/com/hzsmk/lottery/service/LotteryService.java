package com.hzsmk.lottery.service;

import com.hzsmk.common.base.RestResponse;
import com.hzsmk.lottery.req.in.*;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:22
 * @Description:
 */

public interface LotteryService {
    RestResponse getActInfo(GetActInfoIn param);

    RestResponse getActUserInfo(GetActUserInfoIn param);

    RestResponse getCode(GetCodeIn param);

    RestResponse getHelpInfo(GetHelpInfoIn param);

    RestResponse help(HelpIn param);

    RestResponse getMyPrize(GetMyPrizeIn param);

    RestResponse notified(NotifiedIn param);
}
