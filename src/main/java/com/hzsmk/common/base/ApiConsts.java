package com.hzsmk.common.base;

/**
 * 接口相关常量
 *
 * @author jiangjh
 * @date 2019/11/25 10:46
 */
public final class ApiConsts {

    /**
     * 接口调用成功
     */
    public static final String SUCCESS_CODE = "PY0000";
    public static final String SUCCESS_DESC = "成功";

    /**
     * 系统异常,未知错误
     */
    public static final String UNKNOW_ERROR = "PY1999";
    public static final String UNKNOW_ERROR_DESC = "系统异常";

    /**
     * 参数异常
     */
    public static final String PARAM_ERROR = "Error1001";
    public static final String PARAM_ERROR_DESC = "参数异常";

    /**
     * 未登录或已过期
     */
    public static final String LOGIN_ERROR = "Error1002";
    public static final String LOGIN_ERROR_DESC = "未登录或登录已过期";

    /**
     * 业务异常,需要提示信息
     */
    public static final String BUSINESS_ERROR = "Error1003";
    public static final String BUSINESS_ERROR_DESC = "业务异常";

    /**
     * 实名异常,需要提示信息
     */
    public static final String NO_IDENTITY_ERROR = "Error1004";
    public static final String NO_IDENTITY_ERROR_ERROR_DESC = "还没实名认证,请先进行认证";

    /**
     * 还未开通虚拟公交卡,需要提示信息
     */
    public static final String NO_CARD_ERROR = "Error1005";
    public static final String NO_CARD_ERROR_DESC = "还未开通虚拟公交卡，请开通";

    /**
     * 非人才异常
     */
    public static final String NOT_TALENT_ERROR = "Error1006";
    public static final String NOT_TALENT_ERROR_DESC = "您当前非认证人才!";

    /**
     * 人才等级标准异常
     */
    public static final String TALENT_LEVEL_STANDARDS_ERROR = "Error1008";
    public static final String TALENT_LEVEL_STANDARDS_ERROR_DESC="您的人才等级未达到申请条件";


    /**
     * 优惠次数已使用完异常
     */
    public static final String TRAFFIC_ERROR = "Error1007";
    public static final String TRAFFIC_ERROR_DESC = "今日优惠次数已使用完~";

    /**
     * 查询不到人才信息
     */
    public static final String NOUSER_ERROR = "Error0000";
    public static final String NOUSER_ERROR_DESC = "查询不到人才信息!";

    /**
     * 其他异常信息desc, code一律为业务异常的code
     */
    public static final String CODE_EXPIRE_DESC = "验证码已过期";
    public static final String CODE_ERROR_DESC = "验证码错误";
    public static final String USER_ERROR_DESC = "账号或密码错误";
    public static final String ACCOUNT_ERROR_DESC = "账号已停用，请联系管理员";
    public static final String NO_AUTH_DESC = "您无权限操作,请刷新页面并跟管理员联系~";
    /**
     *
     */
    public static final String SOCIAL_BIND_ERROR = "ERROR1009";
    public static final String SOCIAL_BIND_ERROR_DESC = "社保绑定失败，请稍后重试";

    public static final String LOCAL_EXIST = "ERROR_LOCAL_EXIST";
    public static final String LOCAL_EXIST_DESC = "本地人才!访问错误";

    /**
     * 积分商城 订单错误 2000-3000
     */
    public static final String INTEGRAL_ACCOUNT_ERROR = "ERROR2001";
    public static final String INTEGRAL_ACCOUNT_ERROR_DESC = "积分余额不足，请稍后再来";
    public static final String INTEGRAL_PRODUCT_STOCK_ERROR = "ERROR2002";
    public static final String INTEGRAL_PRODUCT_STOCK_ERROR_DESC = "该商品库存不足";

}
