package com.hzsmk.lottery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzsmk.lottery.entity.LotteryActivityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author guowj
 * @email 18698577236@163.com
 * @date 2021-08-29 21:41:05
 */
@Mapper
public interface LotteryActivityDao extends BaseMapper<LotteryActivityEntity> {

    /**
     * 查询参与的活动
     * @param userId
     * @param lotteryStatus
     */
    @Select("<script>" +
            " select a.* from lottery_activity a where 1=1 and " +
            " a.id in (select b.act_id from lottery_act_code b where b.if_delete=0 and b.user_id = #{userId}) and " +
            " a.lottery_status  in " +
            "<foreach collection=\"list\" open=\"(\" separator=\",\" close=\")\" item=\"id\"> " +
            "#{id} " +
            "</foreach> " +
            "and a.if_delete=0  and a.status='1' "+
            "</script>")
    List<LotteryActivityEntity> selectActByUserId(@Param("userId") String userId, @Param("list") List<String> lotteryStatus);
}
