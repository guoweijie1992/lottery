package com.hzsmk.lottery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzsmk.lottery.entity.LotteryActPrizeEntity;
import com.hzsmk.lottery.entity.eo.Prize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author guowj
 * @email 18698577236@163.com
 * @date 2021-08-29 21:41:05
 */
@Mapper
public interface LotteryActPrizeDao extends BaseMapper<LotteryActPrizeEntity> {

    @Select("SELECT " +
            " a.level_desc, " +
            " a.amount, " +
            " a.description, " +
            " a.sort," +
            " a.use_url, " +
            " b.img_url, " +
            " b.prize_name, " +
            " b.prize_price  " +
            "FROM " +
            " lottery_act_prize a, " +
            " lottery_prize b where  a.prize_id = b.id  " +
            " AND a.`status` = '1'  " +
            " AND b.`status` = '1'  " +
            " AND a.act_id = #{id}  " +
            "ORDER BY " +
            " a.sort ")
    List<Prize> selectPrizeByActId(@Param("id")Long id);
}
