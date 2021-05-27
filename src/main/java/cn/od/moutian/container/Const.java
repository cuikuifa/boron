package cn.od.moutian.container;

/*
* 系统字符串常量类
* */
public class Const {
    public static final String pred_type_10100 = "10100";        // 1位纯数字
    public static final String pred_type_10200 = "10200";        // 2位纯数字
    public static final String pred_type_10300 = "10300";        // 3位纯数字
    public static final String pred_type_10400 = "10400";        // 4位纯数字
    public static final String pred_type_10500 = "10500";        // 5位纯数字
    public static final String pred_type_10600 = "10600";        // 6位纯数字
    public static final String pred_type_10700 = "10700";        // 7位纯数字
    public static final String pred_type_10800 = "10800";        // 8位纯数字
    public static final String pred_type_10900 = "10900";        // 9位纯数字
    public static final String pred_type_20100 = "20100";        // 1位纯英文
    public static final String pred_type_20200 = "20200";        // 2位纯英文
    public static final String pred_type_20300 = "20300";        // 3位纯英文
    public static final String pred_type_20400 = "20400";        // 4位纯英文
    public static final String pred_type_20500 = "20500";        // 5位纯英文
    public static final String pred_type_20600 = "20600";        // 6位纯英文
    public static final String pred_type_20700 = "20700";        // 7位纯英文
    public static final String pred_type_20800 = "20800";        // 8位纯英文
    public static final String pred_type_20900 = "20900";        // 9位纯英文
    public static final String pred_type_30100 = "30100";            // 1位数字英文
    public static final String pred_type_30200 = "30200";            // 2位数字英文
    public static final String pred_type_30300 = "30300";            // 3位数字英文
    public static final String pred_type_30400 = "30400";            // 4位数字英文
    public static final String pred_type_30500 = "30500";            // 5位数字英文
    public static final String pred_type_30600 = "30600";            // 6位数字英文
    public static final String pred_type_30700 = "30700";            // 7位数字英文
    public static final String pred_type_30800 = "30800";            // 8位数字英文
    public static final String pred_type_30900 = "30900";            // 9位数字英文
    public static final String pred_type_40100 = "40100";                // 1位汉字
    public static final String pred_type_40200 = "40200";                // 2位汉字
    public static final String pred_type_40300 = "40300";                // 3位汉字
    public static final String pred_type_40400 = "40400";                // 4位汉字
    public static final String pred_type_40500 = "40500";                // 5位汉字
    public static final String pred_type_40600 = "40600";                // 6位汉字
    public static final String pred_type_40700 = "40700";                // 7位汉字
    public static final String pred_type_40800 = "40800";                // 8位汉字
    public static final String pred_type_50100 = "50100";                    // 简单计算题
    public static final String pred_type_50200 = "50200";                    // 复杂计算题

    public static final String REDIS_VAL_CHANNEL_ALIVE = "alive";    // 支付渠道是否可用
    public static final String THREE = "3";
    public static final String POST = "POST";
    public static final String OK = "OK";

    public static final String REDIS_KEY_CHANNEL_ALIVE_SHANGHAIPAY = "channel_alive_shanghaipay";    // 上海渠道方
    public static final String REDIS_KEY_CHANNEL_ALIVE_ABCEPAY = "channel_alive_abcepay";    // 惠农e管家
    public static final String REDIS_KEY_CHANNEL_ALIVE_WXAPP = "channel_alive_wxapp";    // 微信app支付
    public static final String REDIS_KEY_CHANNEL_ALIVE_WXH5 = "channel_alive_wxh5";    // 微信h5支付

    public static final String ISUPLOAD = "y";    // 是否有上传二维码

    public static final String UPLOAD = "isupload";    // 是否有上传二维码的redis字段名

    public static final String SHANGHAIWXPAY = "1";   // 上海微信支付

    public static final String EPAY = "2";   // 惠农e管家支付
    public static final String WXAPPPAY = "3";   // 微信app支付
    public static final String WXH5PAY = "4";   // 微信H5支付

    public static final String GET_PAYMENT_RESULT_JOB = "getPaymentResultJob";
    public static final String PAYMENT_JOB_GROUP = "PaymentResultJobGroup";

    public static final String GET_PAYMENT_RESULT_TRIGGER = "getPaymentResultTrigger";
    public static final String PAYMENT_TRIGGER_GROUP = "payTriggerGroup";

    public static final String MESSAGE = "message";

    public static final String SESSION_IDENTITY = "sessionUser";

    public static final String SUCCESS = "success";
    public static final String PAYSUCCESS = "paysuccess";
    public static final Integer STATES_FORBID = -2;    //禁用

    public static final Integer STATES_AUDIT_FAILED = -1;     //审核不通过

    public static final Integer STATES_DRAFT = 0;      //草稿

    public static final Integer STATES_SUBMIT = 1;   //提交待审核

    public static final Integer STATES_AUDIT_SUCC = 2;   //审核通过

    public static final Integer STATES_INSERT_ERR = 3;   //审核通过，但是数据插入失败

    public static final Integer STATES_EXECUTED = 4; //执行成功

    public static final Integer STATES_DONE = 5; //完成所有步骤

    public static final Integer ENABLED = 0;

    public static final Integer DISABLED = 1;

    public static final Integer SYSADMIN = 1;    //管理员

    public static final Integer NORMAL_USER = 2;   //普通用户

    public static final Integer CONFIG_TYPE_PC = 0;

    public static final Integer CONFIG_TYPE_WEB = 1;

    public static final Integer CONFIG_TYPE_ACCOUNT = 2;

    public static final Integer CONFIG_TYPE_MOBILE = 3;

    /**
     * AES加密的公共KEY
     */
    public static final String AES_KEY = "QQRTKKIOHGFVCXQO";

    public static final String ACTIVEMQ_QUEUE_HT_RT_QRCODE = "htpay.retransmission.qrcode";
    public static final String ACTIVEMQ_QUEUE_HT_RT_CALLBACK = "htpay.retransmission.callback";


}
