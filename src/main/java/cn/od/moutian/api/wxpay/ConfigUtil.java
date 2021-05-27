package cn.od.moutian.api.wxpay;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author zxw
 * @desc:微信支付相关配置信息
 */
public class ConfigUtil {
	/**
	 * 服务号相关信息
	 */
	public final static String APPID = "wxf1edb320da6f36d0";// 应用号

	public final static String API_KEY = "HUNTERKKIOP3638990weuriifkj85232";// API密钥
	public final static String MCH_ID = "1515593031";// 商户号 1218488501 公众号商户id

	public final static String SIGN_TYPE = "MD5";// 签名加密方式
	public final static String TRADE_TYPE_APP = "APP";// 支付类型
	public final static String TRADE_TYPE_MWEB = "MWEB";// 支付类型


	public final static String WAPURL = "MWEB";// wap_url eg:http://g.lotut.com
	public final static String WAPNAME = "MWEB";// wap_name eg:美人




	public static String IP ;
	static {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		IP = addr.getHostAddress(); //获取本机ip
	}
	public static String NOTIFY_URL = "http://" + IP +  ":8060/api/wxapp/callback";

	/**
	 * 微信基础接口地址
	 */
	// 获取token接口(GET)
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 微信支付接口地址--生产环境
     */
    // 微信支付统一接口(POST)
    public final static String PRODUCE_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 微信退款接口(POST)
    public final static String PRODUCE_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    // 订单查询接口(POST)
    public final static String PRODUCE_CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    // 关闭订单接口(POST)
    public final static String PRODUCE_CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    // 退款查询接口(POST)
    public final static String PRODUCE_CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    // 对账单接口(POST)
    public final static String PRODUCE_DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    // 短链接转换接口(POST)
    public final static String PRODUCE_SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
    // 接口调用上报接口(POST)
    public final static String PRODUCE_REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
}
