package com.hzsmk.lottery.entity.eo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Guowj
 * @Classname Prize
 * @Date2021 2021/8/31 0031 16:47
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Prize {
    private String levelDesc;
    private Long prizeId;
    private Integer amount;
    private String description;
    private Integer sort;
    private String imgUrl;
    private String prizeName;
    private Long prizePrice;
    private String useUrl;
}
