package com.hzsmk.lottery.req.out;

import com.hzsmk.lottery.entity.LotteryActivityEntity;
import com.hzsmk.lottery.entity.eo.Prize;
import lombok.Data;

import java.util.List;

/**
 * @author Guowj
 * @Classname GetActInfoOut
 * @Date2021 2021/8/31 0031 16:52
 */
@Data
public class GetActInfoOut  extends LotteryActivityEntity {

    private List<Prize> prizeList;

}
