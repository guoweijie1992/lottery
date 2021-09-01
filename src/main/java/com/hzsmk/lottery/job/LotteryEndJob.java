package com.hzsmk.lottery.job;

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
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LotteryEndJob extends QuartzJobBean {

    @Resource
    private LotteryActivityDao activityDao;


    @Override
    protected void executeInternal(JobExecutionContext context) {
        String seq = TLocalHelper.createSeq();
        Thread.currentThread().setName(seq);
        log.info("********执行抽奖活动定时活动任务 playing-awaiting开始********");
        //进行中的活动查询是否超时
        List<LotteryActivityEntity> list = activityDao.selectList(new QueryWrapper<LotteryActivityEntity>().lambda()
                .eq(LotteryActivityEntity::getIfDelete, LotteryConsts.IFDELETE_N)
                .eq(LotteryActivityEntity::getLotteryStatus, LotteryConsts.LOTTERY_STATUS_PLAYING));
        if(ObjectUtils.isEmpty(list)||list.size()<1){
            log.info("********执行抽奖活动定时活动任务 playing-awaiting结束********");
            return ;
        }
        // 遍历数据,处理
        for (int i = 0, size = list.size(); i < size; i++) {
            try {
                LotteryActivityEntity entity = list.get(i);
                //结束时间超过当前时间则变更为待抽奖
                if(entity.getEndTime().compareTo(new Date())<=0){
                    entity.setLotteryStatus(LotteryConsts.PRIZE_TYPE_AWAITING);
                    int update = activityDao.updateById(entity);
                    if(update<1){
                        log.info("更新活动状态失败:",entity);
                    }
                }
            } catch (Exception e) {
                log.error("执行抽奖活动定时活动任务 playing-awaiting错误:{}", e);
                continue;
            }
        }
        log.info("********执行抽奖活动定时活动任务 playing-awaiting结束********");
    }
}
