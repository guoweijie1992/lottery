package com.hzsmk.common.util;

import com.hzsmk.common.base.EnvironmentEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 判断当前环境
 *
 * @author jiangjh
 * @date 2020/6/8 16:48
 */
@Component
public class EnvUtil {

    private static String configEvn;

    @Value("${spring.profiles.active}")
    public void setConfigEvn(String cEnv) {
        EnvUtil.configEvn = cEnv;
    }

    /**
     * 是否是测试环境
     *
     * @return
     */
    public static boolean isTest() {
        return EnvironmentEnum.TEST.getEnv().equalsIgnoreCase(configEvn);
    }

    /**
     * 是否是开发环境
     *
     * @return
     */
    public static boolean isDefault() {
        return EnvironmentEnum.DEV.getEnv().equalsIgnoreCase(configEvn);
    }

    /**
     * 是否是生产环境
     *
     * @return
     */
    public static boolean isProd() {
        return EnvironmentEnum.PROD.getEnv().equalsIgnoreCase(configEvn);
    }
}
