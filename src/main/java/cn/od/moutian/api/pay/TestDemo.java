package cn.od.moutian.api.pay;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by jacky on 16/9/8.
 * 上海渠道方测试类
 */
public class TestDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        /**
         * 构造请求参数,下面为测试的参数,如果用户自定义,需要new RequestParam() ,把相应的值set进去
         */
//        RequestParam param = RequestParam.createAPPDefault();
//        RequestParam param = RequestParam.createH5Default();
//        RequestParam param = RequestParam.createScanDefault();
        RequestParam param = RequestParam.createByPayment_1("3_");    // 3_
        /**
         * 构造请求,需要请求地址,上面的参数,以及秘钥 都在配置类里面
         */
        long l = System.currentTimeMillis();
        String s = new HttpUtil().makePostRequest(Config.getUrl(), param, Config.getKey());
        long l1 = System.currentTimeMillis();
//        System.out.println("支付方式为微信微信 APP 支付(SDK)");
        System.out.println("支付方式为微信 H5 支付(WAP)" + s);
//        System.out.println("支付方式为微信微信扫码码支付(PC)");

//        解析返回的参数,也可以用来解析支付完成接受通知的参数

        OrderRTO orderRTO = ParseResponse.parseParams(s);

        boolean b = ParseResponse.signCheck(s, Config.getKey());

        PaymentRTO[] payments = orderRTO.getPayments();

        for (int i = 0; i < payments.length; i++) {

            String itemResponseMsg = payments[i].getItemResponseMsg();


            Map<String, String> paramMap = (Map<String, String>) JSON.parse(itemResponseMsg);

            System.out.println(paramMap.get("barcodeUrl"));
            for (Map.Entry<String, String> p : paramMap.entrySet()) {

                System.out.println(p.getKey() + "-" + p.getValue());

            }
        }


    }


    static void main2() {
        RequestParam param = new RequestParam();
        param.setMerchantId("100001");
        param.setTraceNO(String.valueOf(System.currentTimeMillis()));
        new HttpUtil();
        String responseString = HttpUtil.makePostRequest(Config.getUrl(), param, Config.getKey());
        boolean checkResult = ParseResponse.signCheck(responseString, Config.getKey());
        OrderRTO orderRTO = ParseResponse.parseParams(responseString);
        System.out.println("checkResult" + checkResult);
        System.out.println("orderRTO" + orderRTO.toString());
    }

}
