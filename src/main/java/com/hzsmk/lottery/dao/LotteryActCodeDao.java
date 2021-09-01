package com.hzsmk.lottery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzsmk.lottery.entity.LotteryActCodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 
 * 
 * @author guowj
 * @email 18698577236@163.com
 * @date 2021-08-29 21:41:05
 */
@Mapper
public interface LotteryActCodeDao extends BaseMapper<LotteryActCodeEntity> {

    @Update("<script>" +
            "UPDATE lottery_act_code  " +
            "SET prize_status = #{newStatus}  " +
            "WHERE " +
            " if_delete = 0  " +
            " AND act_id = #{actId}  " +
            " AND prize_status != #{condition} "+
            "</script>")
    int updateElse(@Param("actId") Long actId,@Param("condition")String condition,@Param("newStatus")String newStatus);
}
