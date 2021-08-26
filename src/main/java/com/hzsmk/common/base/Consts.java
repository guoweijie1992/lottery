package com.hzsmk.common.base;

/**
 * 常量
 *
 * @author jiangjh
 * @date 2019/9/23 11:14
 */
public final class Consts {

    /**
     * 主数据源
     */
    public static final String PRIMARY_DATASOURCE = "primaryDataSource";

    /**
     * 市民卡人才库数据源
     */
    public static final String SMK_DATASOURCE = "smkDataSource";

    /**
     * 文化旅游卡数据源
     */
    public static final String TOURCARD_DATASOURCE = "tourcardDataSource";

    /**
     * 市民卡车贴库数据源
     */
    public static final String CAR_DATASOURCE = "carDataSource";

    /**
     * 主数据源SqlSessionFactory
     */
    public static final String PRIMARY_SQLSESSION_FACTORY = "primarySqlSessionFactory";

    /**
     * 市民卡SqlSessionFactory
     */
    public static final String SMK_SQLSESSION_FACTORY = "smkSqlSessionFactory";

    /**
     * 文化旅游卡SqlSessionFactory
     */
    public static final String TOURCARD_SQLSESSION_FACTORY = "tourcardSqlSessionFactory";

    /**
     * 车贴SqlSessionFactory
     */
    public static final String CAR_SQLSESSION_FACTORY = "carSqlSessionFactory";

    /**
     * 主数据源transactionManager
     */
    public static final String PRIMARY_TRANSACTION_MANAGER = "primaryTransactionManager";

    /**
     * 市民卡TransactionManager
     */
    public static final String SMK_TRANSACTION_MANAGER = "smkTransactionManager";

    /**
     * 文化旅游卡TransactionManager
     */
    public static final String TOURCARD_TRANSACTION_MANAGER = "tourcardTransactionManager";

    /**
     * 车贴TransactionManager
     */
    public static final String CAR_TRANSACTION_MANAGER = "carTransactionManager";

    /**
     * 主数据源SqlSessionTemplate
     */
    public static final String PRIMARY_SQLSESSION_TEMPLATE = "primarySqlSessionTemplate";

    /**
     * 市民卡SqlSessionTemplate
     */
    public static final String SMK_SQLSESSION_TEMPLATE = "smkSqlSessionTemplate";

    /**
     * 文化旅游卡SqlSessionTemplate
     */
    public static final String TOURCARD_SQLSESSION_TEMPLATE = "tourcardSqlSessionTemplate";

    /**
     * 车贴过户SqlSessionTemplate
     */
    public static final String CAR_SQLSESSION_TEMPLATE = "carSqlSessionTemplate";

    /**
     * 公交地铁刷卡数据源
     */
    public static final String TRAFFIC_DATASOURCE = "trafficDataSource";

    /**
     * 公交地铁刷卡数据源SqlSessionFactory
     */
    public static final String TRAFFIC_SQLSESSION_FACTORY = "trafficSqlSessionFactory";

    /**
     * 公交地铁刷卡数据源transactionManager
     */
    public static final String TRAFFIC_TRANSACTION_MANAGER = "trafficTransactionManager";

    /**
     * 公交地铁刷卡数据源SqlSessionTemplate
     */
    public static final String TRAFFIC_SQLSESSION_TEMPLATE = "trafficSqlSessionTemplate";

    /**
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 验证码key前缀
     */
    public static final String CAPTCHA_PRIFIX = "captcha:";

    /**
     * 登录用户key前缀
     */
    public static final String LOGINUSER_PRIFIX = "userToken:";

    /**
     * 未知字符串
     */
    public static final String UNKNOWN = "unknown";

    /**
     * 登录方法名称
     */
    public static final String LOGIN_METHOD_NAME = "login";

    /**
     * 菜单树顶级节点id
     */
    public static final Long ROOT_NODE_ID = 0L;

    /**
     * 请求日志成功状态
     */
    public static final Integer LOG_STATUS_SUCCESS = 1;

    /**
     * 请求日志失败状态
     */
    public static final Integer LOG_STATUS_ERROR = 0;

    /**
     * 用户已授权
     */
    public static final String USER_AUTH_FLAG = "1";

    /**
     * 用户未授权
     */
    public static final String UN_USER_AUTH_FLAG = "0";

    /**
     * admin账号,角色
     */
    public static final String ADMIN_USERNAME = "admin";

    /**
     * 默认字符编码
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 人才二维码前缀
     */
    public static final String QRCODE_PREFIX = "SRC";

    /**
     * 16进制字符串前缀
     */
    public static final String QRCODE_PRE = "1";

    /**
     * 车牌补贴打款备注
     */
    public static final String PAY_CAR_REMARK = "车牌竞价补贴";

    /**
     * 用户反馈审核通过状态
     */
    public static final int FEEDBACK_PASS = 1;

    /**
     * 用户反馈审核不通过状态
     */
    public static final int FEEDBACK_UNPASS = 0;

    /**
     * 人才用户已经激活
     */
    public static final Integer USER_ACTIVE = 1;

    /**
     * 用户未激活
     */
    public static final Integer USER_DISACTIVE = 0;

    /**
     * 外部调用我们接口验签, 30分钟过期
     */
    public static final long IN_EXPRIT_TIME = 30 * 60 * 1000;

    /**
     * 二维码失效时间,70秒
     */
    public static final long QR_EXPIRE_TIME = 70 * 1000;

    /**
     * 白名单用户来源(手工添加)
     */
    public static final String MANUAL_ADD = "手工添加";

    /**
     * 白名单用户来源(人社数据)
     */
    public static final String REN_SHEN = "人社数据";

    /**
     * 租房补贴是否可以审核
     */
    public static final Integer CAN_OPEARTION = 1;

    /**
     * 杭州银行接口成功返回code
     */
    public static final String HZBANK_SUC_CODE = "000000";

    /**
     * 杭州银行代发-支付成功
     */
    public static final String HZBANK_PAY_SUC_CODE = "90";

    /**
     * 杭州银行代发-支付失败
     */
    public static final String HZBANK_PAY_ERROR_CODE = "91";

    /**
     * 杭州银行,代发结果失败通知
     */
    public static final String HZBANK_PAY_RESULT_ERROR = "<orderState>91</orderState>";


    /**
     * 社保-养老保险文案
     */
    public static final String DEAFULT_SOCIAL_CONTENT = "养老保险";

    /**
     * 社保-生育保险文案
     */
    public static final String DEAFULT_SHENGYU_SOCIAL_CONTENT = "生育保险";

    /**
     * 失业保险
     */
    public static final String DEAFULT_WORK_SOCIAL_CONTENT = "失业保险";

    /**
     * 职工医疗保险
     */
    public static final String DEAFULT_YILIAO_ZHIGONG_SOCIAL_CONTENT = "职工医保";

    /**
     * 医疗保险
     */
    public static final String DEAFULT_YILIAO_SOCIAL_CONTENT = "医疗保险";

    /**
     * 大学生医疗保险
     */
    public static final String COLLEGE_STUDENT = "城乡居民基本医疗保险";

    /**
     * 企业信用代码
     */
    public static final String CERT_CODE = "bAB010";

    /**
     * 参保时间
     */
    public static final String SOCIAL_TIME = "aAE030";

    /**
     * areaCode
     */
    public static final String AREA_CODE = "areaCode";

    /**
     * company
     */
    public static final String COMPANY = "company";

}
