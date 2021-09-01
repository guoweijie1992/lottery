package com.hzsmk.lottery.job;

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

    @Value("${lottery.start.cron}")
    private String lotteryStartCron;

    @Value("${lottery.end.cron}")
    private String lotteryEndCron;

    @Value("${lottery.draw.cron}")
    private String lotteryDrawCron;


    /**
     * 把jobDetail注册到Cron表达式的trigger上去
     *
     * @return
     */
    @Bean
    public Trigger lotteryEndCronTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(lotteryEndCron);
        return TriggerBuilder.newTrigger()
                .forJob(lotteryEndScheduleDetail())
                .withIdentity("lotteryEndCronTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    /**
     *
     *
     * @return
     */
    @Bean
    public JobDetail lotteryEndScheduleDetail() {
        return JobBuilder.newJob(LotteryEndJob.class).withIdentity("lotteryEndJob").storeDurably().build();
    }


    /**
     * 把jobDetail注册到Cron表达式的trigger上去
     *
     * @return
     */
    @Bean
    public Trigger lotteryStartCronTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(lotteryStartCron);
        return TriggerBuilder.newTrigger()
                .forJob(lotteryEndScheduleDetail())
                .withIdentity("lotteryStartCronTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    /**
     * 住房补贴每月定时审核任务
     *
     * @return
     */
    @Bean
    public JobDetail lotteryStartScheduleDetail() {
        return JobBuilder.newJob(LotteryStartJob.class).withIdentity("lotteryStartJob").storeDurably().build();
    }
}
