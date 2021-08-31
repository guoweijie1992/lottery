package com.hzsmk.lottery.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzsmk.common.base.ApiConsts;
import com.hzsmk.common.base.RestResponse;
import com.hzsmk.common.exception.BusinessException;
import com.hzsmk.common.util.SmkAppUser;
import com.hzsmk.common.util.SmkTokenUtil;
import com.hzsmk.lottery.consts.LotteryConsts;
import com.hzsmk.lottery.dao.LotteryActCodeDao;
import com.hzsmk.lottery.dao.LotteryActivityDao;
import com.hzsmk.lottery.entity.LotteryActCodeEntity;
import com.hzsmk.lottery.entity.LotteryActivityEntity;
import com.hzsmk.lottery.reqIn.GetActInfoIn;
import com.hzsmk.lottery.reqIn.GetActUserInfoIn;
import com.hzsmk.lottery.reqIn.GetCodeIn;
import com.hzsmk.lottery.reqIn.GetHelpInfoIn;
import com.hzsmk.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: 18698
 * @Date: 2021/8/29 21:22
 * @Description:
 */
@Service
public class LotteryServiceImpl implements LotteryService {

    @Resource
    private LotteryActivityDao activityDao;
    @Resource
    private LotteryActCodeDao actCodeDao;

    @Autowired
    private SmkTokenUtil smkTokenUtil;

    @Override
    public RestResponse getActInfo(GetActInfoIn param) {
        return checkActStatus(param.getActId());
    }

    private RestResponse checkActStatus(Long actId) {
        /**
         * 1.根据id获取活动
         * 2.判断活动是否存在，状态是否正确
         * 3.返回活动
         */
        //1.根据id获取活动
        LotteryActivityEntity act = activityDao.selectOne(new QueryWrapper<LotteryActivityEntity>().lambda()
                .eq(LotteryActivityEntity::getId, actId)
                .eq(LotteryActivityEntity::getIfDelete, "0")
                .last("limit 1 "));
        //2.判断活动是否存在，状态是否正确
        if (ObjectUtil.isNull(act)) {
            return RestResponse.errorBusinessResult("活动不存在！");
        }
        if (!"1".equals(act.getStatus())) {
            return RestResponse.errorBusinessResult("活动暂不可用！");
        }
        return RestResponse.successResult(act);
    }

    @Override
    public RestResponse getActUserInfo(GetActUserInfoIn param) {
        Map ret = new HashMap<>();
        /**
         * 1.获取活动id关联查询对应得用户参与记录
         *
         */
        SmkAppUser appUser = smkTokenUtil.getAppUser(param.getAccessToken());
        if (ObjectUtil.isNull(appUser)) {
            return RestResponse.errorBusinessResult("token解析失败");
        }
        //根据活动id查询code列表
        List<LotteryActCodeEntity> codeList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                .eq(LotteryActCodeEntity::getActId, param.getActId()));
        List<String> actCodeList = new LinkedList<>();
        if (ObjectUtil.isEmpty(codeList)) {
            ret.put("attendStatus", "unjoin");
        } else {
            ret.put("attendStatus", "joined");
            //若有记录返回code列表
            for (LotteryActCodeEntity entity : codeList) {
                actCodeList.add(entity.getLotteryCode());
            }
        }
        ret.put("actCodeList", actCodeList);
        return RestResponse.successResult(ret);
    }


    @Override
    public RestResponse getCode(GetCodeIn param) {
        Map ret=new HashMap<>();
        /**
         * 1.判断活动状态
         * 2.判断用户是否可参与
         * 3.生成抽奖码
         */
        //1.判断活动状态
        RestResponse restResponse = checkActStatus(param.getActId());
        if (!ApiConsts.SUCCESS_CODE.equals(restResponse.getCode())) {
            return restResponse;
        }
        //2.判断用户可参与状态
        LotteryActivityEntity act = (LotteryActivityEntity) restResponse.getResponse();
        SmkAppUser appUser = smkTokenUtil.getAppUser(param.getAccessToken());
        checkUserStatus(act, appUser);
        //3.生成奖券，保存数据库
        //todo  lotteryCode生成策略
        String lotteryCode = RandomUtil.randomString(8);
        //判断是否首次参加活动
        Integer ifFirst = ifFirstJoin(appUser);
        LotteryActCodeEntity entity = new LotteryActCodeEntity(act.getId(), appUser.getUserId(), appUser.getMobile(),
                lotteryCode, LotteryConsts.LOTTERY_STATUS_PLAYING, new Date(), LotteryConsts.IFDELETE_N, LotteryConsts.CODETYPE_SELF, ifFirst);
        int insert = actCodeDao.insert(entity);
        if (insert > 0) {
            ret.put("lotteryCode",lotteryCode);
            return RestResponse.successResult(ret);
        } else {
            throw new BusinessException("数据库保存失败");
        }
    }

    /**
     * 获取助力页面详情
     * @param param
     * @return
     */
    @Override
    public RestResponse getHelpInfo(GetHelpInfoIn param) {
        Map ret=new HashMap();
        //被助力手机号
        String mobile = param.getMobile();

        return null;
    }

    /**
     * 判断是否第一次参加活动
     *
     * @param appUser
     * @return
     */
    private Integer ifFirstJoin(SmkAppUser appUser) {
        List<LotteryActCodeEntity> entityList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                .eq(LotteryActCodeEntity::getUserId, appUser.getUserId())
                .eq(LotteryActCodeEntity::getIfDelete, LotteryConsts.IFDELETE_N));
        if (ObjectUtil.isEmpty(entityList)) {
            return LotteryConsts.FIRST_JOIN_Y;
        } else {
            return LotteryConsts.FIRST_JOIN_N;
        }
    }

    /**
     * 校验用户抽奖状态
     *
     * @param act
     * @param appUser
     */
    private void checkUserStatus(LotteryActivityEntity act, SmkAppUser appUser) {
        String userId = appUser.getUserId();
        //自抽奖数量
        Integer drawCounts = act.getDrawCounts();
        //自抽奖频率
        String drawFrequency = act.getDrawFrequency();
        //最大奖券数量
        Integer maxCodeCounts = act.getMaxCodeCounts();
        //day week around
        Date date = new Date();
        if (!LotteryConsts.LOTTERY_STATUS_PLAYING.equals(act.getLotteryStatus())) {
            throw new BusinessException("当前活动状态不可参与");
        }
        //获取当前用户总奖券
        List<LotteryActCodeEntity> entityList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                .eq(LotteryActCodeEntity::getUserId, userId)
                .eq(LotteryActCodeEntity::getIfDelete, LotteryConsts.IFDELETE_N)
        );
        //若为空则直接返回
        if(ObjectUtil.isEmpty(entityList)){
            return;
        }
        //判断是否已达到最大奖券数量
        if(entityList.size()>=maxCodeCounts){
            throw new BusinessException("奖券数量已达到上限");
        }
        if (LotteryConsts.DRAWFREQUENCY_DAY.equals(drawFrequency)) {
            //当天的抽奖数量
            List<LotteryActCodeEntity> collect = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_SELF)
                    && p.getCreateTime().after(DateUtil.beginOfDay(date))
                    && p.getCreateTime().before(DateUtil.endOfDay(date))).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(collect) && collect.size() >= drawCounts) {
                throw new BusinessException("超过限定抽奖次数");
            }
        }
        if (LotteryConsts.DRAWFREQUENCY_WEEK.equals(drawFrequency)) {
            //当周的抽奖数量
            List<LotteryActCodeEntity> collect = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_SELF)
                    && p.getCreateTime().after(DateUtil.beginOfWeek(date))
                    && p.getCreateTime().before(DateUtil.endOfWeek(date))).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(collect) && collect.size() >= drawCounts) {
                throw new BusinessException("超过限定抽奖次数");
            }
        }
        if (LotteryConsts.DRAWFREQUENCY_AROUND.equals(drawFrequency)) {
            //当期的抽奖数量
            List<LotteryActCodeEntity> collect = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_SELF)
                    && p.getCreateTime().after(act.getStartTime())
                    && p.getCreateTime().before(act.getEndTime())).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(collect) && collect.size() >= drawCounts) {
                throw new BusinessException("超过限定抽奖次数");
            }
        }
    }
}
