package com.hzsmk.talentcode.job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 定时任务配置
 *
 * @author jiangjh
 * @date 2020/4/26 18:31
 */
@Configuration
@Profile(value = {"prod", "default"})
public class QuartzConfiguration {





}
