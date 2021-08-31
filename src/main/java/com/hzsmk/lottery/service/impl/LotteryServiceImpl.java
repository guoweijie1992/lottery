package com.hzsmk.lottery.service.impl;

import cn.hutool.core.bean.BeanUtil;
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
import com.hzsmk.lottery.dao.*;
import com.hzsmk.lottery.entity.*;
import com.hzsmk.lottery.entity.eo.Prize;
import com.hzsmk.lottery.req.in.*;
import com.hzsmk.lottery.req.out.GetActInfoOut;
import com.hzsmk.lottery.req.out.GetMyPrizeAct;
import com.hzsmk.lottery.req.out.GetMyPrizeOut;
import com.hzsmk.lottery.service.LotteryService;
import com.hzsmk.lottery.service.thrid.SmkUserSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private LotteryActPrizeDao actPrizeDao;
    @Resource
    private LotteryPrizeDao prizeDao;
    @Resource
    private LotteryHelpRecordDao helpRecordDao;

    @Autowired
    private SmkTokenUtil smkTokenUtil;
    @Autowired
    private SmkUserSystem smkUserSystem;

    @Override
    public RestResponse getActInfo(GetActInfoIn param) {
        LotteryActivityEntity act = (LotteryActivityEntity) checkActStatus(param.getActId()).getResponse();
        //3.获取活动对应奖品
        List<Prize> prizes = actPrizeDao.selectPrizeByActId(act.getId());
        //4.组装返回参数
        GetActInfoOut getActInfoOut = new GetActInfoOut();
        BeanUtil.copyProperties(act, getActInfoOut);
        getActInfoOut.setPrizeList(prizes);
        return RestResponse.successResult(getActInfoOut);
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
        //2.根据活动id查询code列表
        List<LotteryActCodeEntity> codeList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                .eq(LotteryActCodeEntity::getActId, param.getActId())
                .eq(LotteryActCodeEntity::getIfDelete, LotteryConsts.IFDELETE_N));
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
        ret.put("mobile", appUser.getMobile());
        return RestResponse.successResult(ret);
    }


    @Override
    public RestResponse getCode(GetCodeIn param) {
        Map ret = new HashMap<>();
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
        LotteryActivityEntity act = (LotteryActivityEntity) restResponse.getResponse();
        //2.判断用户可参与状态
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
            ret.put("lotteryCode", lotteryCode);
            return RestResponse.successResult(ret);
        } else {
            throw new BusinessException("数据库保存失败");
        }
    }

    /**
     * 获取助力页面详情
     *
     * @param param
     * @return
     */
    @Override
    public RestResponse getHelpInfo(GetHelpInfoIn param) {
        Map ret = new HashMap();
        //被助力手机号
        String mobile = param.getMobile();
        //获取被助力用户userId
        String userId = smkUserSystem.getUserIdByMobile(mobile);
        //根据用户userId获取用户头像
        Map userInfo = smkUserSystem.getUserInfo(userId);
        //获取活动
        RestResponse restResponse = checkActStatus(param.getActId());
        if (!ApiConsts.SUCCESS_CODE.equals(restResponse.getCode())) {
            return restResponse;
        }
        //组装返回数据
        LotteryActivityEntity act = (LotteryActivityEntity) restResponse.getResponse();
        ret.put("mobile", mobile.substring(0, 3) + "****" + mobile.substring(7, 11));
        ret.put("lotteryName", act.getLotteryName());
        ret.put("endTime", act.getEndTime());
        ret.put("lotteryStatus", act.getLotteryStatus());
        ret.put("shareImgUrl", act.getShareImgUrl());
        ret.put("headImgUrl", userInfo.get("headImageUrl"));
        //返回前端
        return RestResponse.successResult(ret);
    }

    /**
     * 助力获取奖券
     *
     * @param param
     * @return
     */
    @Override
    @Transactional
    public RestResponse help(HelpIn param) {
        Map ret = new HashMap();
        //1.获取活动并判断活动状态是否可参与
        RestResponse restResponse = checkActStatus(param.getActId());
        if (!ApiConsts.SUCCESS_CODE.equals(restResponse.getCode())) {
            return restResponse;
        }
        LotteryActivityEntity act = (LotteryActivityEntity) restResponse.getResponse();
        //2.判断助力状态
        String accessToken = param.getAccessToken();
        //被助力手机号
        String mobile = param.getMobile();
        //获取被助力用户userId
        String userId = smkUserSystem.getUserIdByMobile(mobile);
        //获取助力用户信息
        SmkAppUser appUser = smkTokenUtil.getAppUser(accessToken);
        checkHelpStatus(act, appUser, userId);
        //3.生成奖券，保存数据库
        //todo  lotteryCode生成策略
        String lotteryCode = RandomUtil.randomString(8);
        //判断是否首次参加活动
        Integer ifFirst = ifFirstJoin(userId);
        LotteryActCodeEntity entity = new LotteryActCodeEntity(act.getId(), userId, mobile,
                lotteryCode, LotteryConsts.LOTTERY_STATUS_PLAYING, new Date(), LotteryConsts.IFDELETE_N, LotteryConsts.CODETYPE_HELP, ifFirst);
        int insert = actCodeDao.insert(entity);
        if (insert > 0) {
            ret.put("lotteryCode", lotteryCode);
        } else {
            throw new BusinessException("数据库保存失败");
        }
        LotteryHelpRecordEntity helpRecordEntity = new LotteryHelpRecordEntity(appUser.getUserId(), appUser.getMobile(), null, entity.getId(), new Date(), act.getId());
        int insert1 = helpRecordDao.insert(helpRecordEntity);
        if (insert1 > 0) {
            return RestResponse.successResult(ret);
        } else {
            throw new BusinessException("数据库保存失败");
        }


    }

    /**
     * 获取我的奖品
     *
     * @param param
     * @return
     */
    @Override
    public RestResponse getMyPrize(GetMyPrizeIn param) {
        GetMyPrizeOut getMyPrizeOut = new GetMyPrizeOut();
        String accessToken = param.getAccessToken();
        SmkAppUser appUser = smkTokenUtil.getAppUser(accessToken);
        String userId = appUser.getUserId();
        //待开奖的
        if (LotteryConsts.PRIZE_TYPE_AWAITING.equals(param.getType())) {
            List<GetMyPrizeAct> awaitingList = new ArrayList<>();
            //获取当前用户参与的进行中或待开奖的活动
            List<String> lotteryStatus = new LinkedList<String>() {{
                add(LotteryConsts.LOTTERY_STATUS_AWAITING);
                add(LotteryConsts.LOTTERY_STATUS_PLAYING);
            }};
            List<LotteryActivityEntity> list = activityDao.selectActByUserId(userId, lotteryStatus);
            //循环获取活动的券码
            for (LotteryActivityEntity lotteryActivityEntity : list) {
                GetMyPrizeAct getMyPrizeAct = new GetMyPrizeAct();
                List<LotteryActCodeEntity> codeList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                        .eq(LotteryActCodeEntity::getActId, lotteryActivityEntity.getId())
                        .eq(LotteryActCodeEntity::getUserId,userId)
                        .eq(LotteryActCodeEntity::getIfDelete, LotteryConsts.IFDELETE_N));
                //若有记录返回code列表
                List<String> actCodeList = new LinkedList<>();
                //将券码存到列表中
                for (LotteryActCodeEntity entity : codeList) {
                    actCodeList.add(entity.getLotteryCode());
                }
                List<Prize> prizes = actPrizeDao.selectPrizeByActId(lotteryActivityEntity.getId());
                //组装返回参数
                BeanUtil.copyProperties(lotteryActivityEntity, getMyPrizeAct);
                getMyPrizeAct.setLotteryId(lotteryActivityEntity.getId());
                getMyPrizeAct.setActCodeList(actCodeList);
                getMyPrizeAct.setImgUrl(prizes.get(0).getImgUrl());
                awaitingList.add(getMyPrizeAct);
            }
            getMyPrizeOut.setAwaitingList(awaitingList);
        }
        //已完成的
        if (LotteryConsts.PRIZE_TYPE_COMPLETED.equals(param.getType())) {
            List<GetMyPrizeAct> completedList = new ArrayList<>();
            //获取当前用户参与的已完成的活动
            List<String> lotteryStatus = new LinkedList<String>() {{
                add(LotteryConsts.LOTTERY_STATUS_COMPLETED);
            }};
            List<LotteryActivityEntity> list = activityDao.selectActByUserId(userId, lotteryStatus);
            //循环活动列表，判断用户是否中奖
            for (LotteryActivityEntity lotteryActivityEntity : list) {
                GetMyPrizeAct getMyPrizeAct = new GetMyPrizeAct();
                //todo 还没做完
                List<LotteryActCodeEntity> codeList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                        .eq(LotteryActCodeEntity::getActId, lotteryActivityEntity.getId())
                        .eq(LotteryActCodeEntity::getUserId,userId)
                        .eq(LotteryActCodeEntity::getIfDelete, LotteryConsts.IFDELETE_N));
                //按照中奖状态进行排序
                List<LotteryActCodeEntity> collect = codeList.stream().sorted(Comparator.comparing(LotteryActCodeEntity::getPrizeStatus).reversed()).collect(Collectors.toList());
                LotteryActCodeEntity lotteryCode = collect.get(0);
                if(LotteryConsts.PRIZE_STATUS_WIN.equals(lotteryCode.getPrizeStatus())){
                    //如果中奖了需要取出奖品ID，查询奖品图片
                    LotteryPrizeEntity prize=prizeDao.selectById(lotteryCode.getPrizeId());
                    getMyPrizeAct.setPrizePrice(prize.getPrizePrice());
                    getMyPrizeAct.setImgUrl(prize.getImgUrl());
                }





                completedList.add(getMyPrizeAct);
            }
            getMyPrizeOut.setCompletedList(completedList);
        }
        return RestResponse.successResult(getMyPrizeOut);
    }

    /**
     * 判断助力状态
     *
     * @param act     活动
     * @param appUser 助力用户
     * @param userId  被助力用户ID
     */
    private void checkHelpStatus(LotteryActivityEntity act, SmkAppUser appUser, String userId) {
        //设定的最大奖券数量
        Integer maxCodeCounts = act.getMaxCodeCounts();
        if (!LotteryConsts.LOTTERY_STATUS_PLAYING.equals(act.getLotteryStatus())) {
            throw new BusinessException("当前活动状态不可参与");
        }
        //获取被助力用户总奖券
        List<LotteryActCodeEntity> entityList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                .eq(LotteryActCodeEntity::getUserId, userId)
                .eq(LotteryActCodeEntity::getIfDelete, LotteryConsts.IFDELETE_N)
        );
        //若为空则直接返回
        if (ObjectUtil.isEmpty(entityList)) {
            return;
        }
        //判断是否已达到最大奖券数量
        if (entityList.size() >= maxCodeCounts) {
            throw new BusinessException("奖券数量已达到上限");
        }
        //设定的可被助力数量
        Integer maxBeHelp = act.getMaxBeHelp();
        long count = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_HELP)).count();
        if (count >= maxBeHelp) {
            throw new BusinessException("助力奖券数量已达上限");
        }
        //设定的单人助力数量
        Integer maxHelp = act.getMaxHelp();
        //获取助力用户助力的奖券总数量


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
     * 判断是否第一次参加活动
     *
     * @param userId
     * @return
     */
    private Integer ifFirstJoin(String userId) {
        List<LotteryActCodeEntity> entityList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda()
                .eq(LotteryActCodeEntity::getUserId, userId)
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
        if (ObjectUtil.isEmpty(entityList)) {
            return;
        }
        //判断是否已达到最大奖券数量
        if (entityList.size() >= maxCodeCounts) {
            throw new BusinessException("奖券数量已达到上限");
        }
        if (LotteryConsts.DRAWFREQUENCY_DAY.equals(drawFrequency)) {
            //当天的抽奖数量
            long collect = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_SELF)
                    && p.getCreateTime().after(DateUtil.beginOfDay(date))
                    && p.getCreateTime().before(DateUtil.endOfDay(date))).count();
            if (collect >= drawCounts) {
                throw new BusinessException("超过个人限定抽奖次数");
            }
        }
        if (LotteryConsts.DRAWFREQUENCY_WEEK.equals(drawFrequency)) {
            //当周的抽奖数量
            long collect = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_SELF)
                    && p.getCreateTime().after(DateUtil.beginOfWeek(date))
                    && p.getCreateTime().before(DateUtil.endOfWeek(date))).count();
            if (collect >= drawCounts) {
                throw new BusinessException("超过个人限定抽奖次数");
            }
        }
        if (LotteryConsts.DRAWFREQUENCY_AROUND.equals(drawFrequency)) {
            //当期的抽奖数量
            long collect = entityList.stream().filter(p -> p.getCodeType().equals(LotteryConsts.CODETYPE_SELF)
                    && p.getCreateTime().after(act.getStartTime())
                    && p.getCreateTime().before(act.getEndTime())).count();
            if (collect >= drawCounts) {
                throw new BusinessException("超过个人限定抽奖次数");
            }
        }
    }

}
