package com.hzsmk.common.util;

import org.springframework.core.env.Environment;

public class EnvironmentUtil {
    /**
     * 获取 配置文件中的数据
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return SpringContextUtils.getBean(Environment.class).getProperty(key);
    }
}
