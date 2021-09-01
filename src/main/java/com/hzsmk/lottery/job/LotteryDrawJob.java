package com.hzsmk.lottery.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzsmk.common.util.TLocalHelper;
import com.hzsmk.lottery.consts.LotteryConsts;
import com.hzsmk.lottery.dao.LotteryActivityDao;
import com.hzsmk.lottery.entity.LotteryActivityEntity;
import com.hzsmk.lottery.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Guowj
 * @Classname LotteryEndJob
 * @Date2021 2021/9/1 0001 15:14
 */
@Slf4j
@DisallowConcurrentExecution
@Service
public class LotteryDrawJob extends QuartzJobBean {

    @Resource
    private LotteryActivityDao activityDao;

    @Autowired
    private LotteryService lotteryService;

    @Value("${draw.before.minutes}")
    private Integer draw_bef_min;


    @Override
    protected void executeInternal(JobExecutionContext context) {
        String seq = TLocalHelper.createSeq();
        Thread.currentThread().setName(seq);
        log.info("********执行抽奖活动定时活动任务 awaiting-completed开始********");
        //进行中的活动查询是否超时
        List<LotteryActivityEntity> list = activityDao.selectList(new QueryWrapper<LotteryActivityEntity>().lambda()
                .eq(LotteryActivityEntity::getIfDelete, LotteryConsts.IFDELETE_N)
                .eq(LotteryActivityEntity::getLotteryStatus, LotteryConsts.PRIZE_TYPE_AWAITING));
        if(ObjectUtils.isEmpty(list)||list.size()<1){
            log.info("********执行抽奖活动定时活动  awaiting-completed结束********");
            return ;
        }
        // 遍历数据,处理
        for (int i = 0, size = list.size(); i < size; i++) {
            try {
                LotteryActivityEntity entity = list.get(i);
                //抽奖时间预留指定时间.获取真实的开奖时间
                DateTime realDrawTime = DateUtil.offsetMinute(new Date(), draw_bef_min);
                if(entity.getDrawTime().compareTo(realDrawTime)>=0){
                    lotteryService.draw(entity);
                }
            } catch (Exception e) {
                log.error("执行抽奖活动定时活动任务 awaiting-completed错误:{}", e);
                continue;
            }
        }
        log.info("********执行抽奖活动定时活动任务 awaiting-completed结束********");
    }
}
