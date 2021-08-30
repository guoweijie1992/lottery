package com.hzsmk.lottery.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzsmk.common.base.RestResponse;
import com.hzsmk.common.util.SmkAppUser;
import com.hzsmk.common.util.SmkTokenUtil;
import com.hzsmk.lottery.dao.LotteryActCodeDao;
import com.hzsmk.lottery.dao.LotteryActivityDao;
import com.hzsmk.lottery.entity.LotteryActCodeEntity;
import com.hzsmk.lottery.entity.LotteryActivityEntity;
import com.hzsmk.lottery.reqIn.GetActInfoIn;
import com.hzsmk.lottery.reqIn.GetActUserInfoIn;
import com.hzsmk.lottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        /**
         * 1.根据id获取活动
         * 2.判断活动是否存在，状态是否正确
         * 3.返回活动
         */
        //1.根据id获取活动
        LotteryActivityEntity act = activityDao.selectById(param.getActId());
        //2.判断活动是否存在，状态是否正确
        if(ObjectUtil.isNull(act)){
            return RestResponse.errorBusinessResult("活动不存在！");
        }
        if(!"1".equals(act.getStatus())){
            return RestResponse.errorBusinessResult("活动暂不可用！");
        }
        return  RestResponse.successResult(act);
    }

    @Override
    public RestResponse getActUserInfo(GetActUserInfoIn param) {
        Map ret=new HashMap<>();
        /**
         * 1.获取活动id关联查询对应得用户参与记录
         * 2.获取
         */
        SmkAppUser appUser = smkTokenUtil.getAppUser(param.getAccessToken());
        if(ObjectUtil.isNull(appUser)){
            return RestResponse.errorBusinessResult("token解析失败");
        }
        //根据活动id查询code列表
        List<LotteryActCodeEntity> codeList = actCodeDao.selectList(new QueryWrapper<LotteryActCodeEntity>().lambda().eq(LotteryActCodeEntity::getActId, param.getActId()));
        List<String> actCodeList=new LinkedList<>();
        if(ObjectUtil.isEmpty(codeList)){
            ret.put("attendStatus","unjoin");
        }else{
            ret.put("attendStatus","joined");
            for (LotteryActCodeEntity entity : codeList) {
                actCodeList.add(entity.getLotteryCode());
            }
        }
        ret.put("actCodeList",actCodeList);
        return RestResponse.successResult(ret);
    }
}
