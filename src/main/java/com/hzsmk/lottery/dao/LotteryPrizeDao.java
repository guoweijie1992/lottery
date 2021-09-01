package com.hzsmk.lottery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzsmk.lottery.entity.LotteryPrizeEntity;
import com.hzsmk.lottery.entity.eo.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * 
 * @author guowj
 * @email 18698577236@163.com
 * @date 2021-08-29 21:41:05
 */
@Mapper
public interface LotteryPrizeDao extends BaseMapper<LotteryPrizeEntity> {

    @Select("SELECT " +
            " b.level_desc, " +
            " b.amount, " +
            " b.description, " +
            " b.sort, " +
            " b.use_url, " +
            " a.img_url, " +
            " a.prize_name, " +
            " a.prize_price  " +
            "FROM " +
            " lottery_prize a, " +
            " lottery_act_prize b where  b.prize_id = a.id  " +
            " AND a.`status` = '1'  " +
            " AND b.`status` = '1'  " +
            " AND a.id= #{prizeId} "+
            " AND b.act_id= #{actId} ")
    Prize selectByPrizeIdAndActId(@Param("prizeId") Long prizeId,@Param("actId") Long actId);
}
