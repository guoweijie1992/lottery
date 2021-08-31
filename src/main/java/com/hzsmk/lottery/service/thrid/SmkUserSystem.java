package com.hzsmk.lottery.service.thrid;

import java.util.Map;

/**
 * @author Guowj
 * @Classname SmkUserSystem
 * @Date2021 2021/8/31 0031 14:26
 */
public interface SmkUserSystem {
    String getUserIdByMobile(String mobile);

    Map getUserInfo(String userId);
}
