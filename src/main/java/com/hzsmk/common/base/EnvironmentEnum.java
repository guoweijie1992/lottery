package com.hzsmk.common.base;

/**
 * 测试环境,DEV环境,生产环境枚举
 *
 * @author jiangjh
 * @date 2019/9/11 10:25
 */
public enum EnvironmentEnum {

    DEV("default"),
    TEST("test"),
    PROD("prod");

    private String env;

    EnvironmentEnum(String env) {
        this.env = env;
    }

    public String getEnv() {
        return env;
    }
}
