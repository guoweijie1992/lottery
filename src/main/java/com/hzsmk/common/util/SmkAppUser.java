package com.hzsmk.common.util;

import lombok.Data;

import java.io.Serializable;

/**
 * smkAPP用户token解析出来的实体
 *
 * @author jiangjh
 * @date 2020/4/3 11:23
 */
@Data
public class SmkAppUser implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 用户等级
     */
    private String level;
}
