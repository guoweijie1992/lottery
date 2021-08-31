package com.hzsmk.lottery.req.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * @author Guowj
 * @Classname GetMyPrizeOut
 * @Date2021 2021/8/31 0031 19:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMyPrizeOut {
    List<GetMyPrizeAct> completedList;
    List<GetMyPrizeAct> awaitingList;
}
