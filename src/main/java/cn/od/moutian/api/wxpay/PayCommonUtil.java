package cn.od.moutian.api.wxpay;

import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author zxw
 * @desc:微信支付随机字符串和签名工具
 */
@Slf4j
public class PayCommonUtil {

	/**
	 * 自定义长度随机字符串
	 * @param length
	 * @return
	 */
	public static String CreateNoncestr(int length){
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_|*";
		String res = new Date().getTime() + "";
		int temp = length - res.length();
		if (temp <= 0){
			return null;
		}
		for (int i = 0; i < temp; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
    
	/**
	 * 默认16 位随机字符串
	 * @return
	 */
	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
	
	public static void main(String[] args) {
		String createNoncestr = PayCommonUtil.CreateNoncestr();
		System.out.println(createNoncestr);

		try {
			System.out.println(CreateNoncestr(32));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 签名工具
	 * @author zxw
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式 UTF-8
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSign(String characterEncoding,
			Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Entry <String,Object>entry = (Entry<String,Object>) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();//去掉带sign的项
			if (null != value && !"".equals(value) && !"sign".equals(key)
					&& !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		sb.append("key=" + ConfigUtil.API_KEY /*ConfigUtil.SANDBOXNEW_SIGNKEY*/);
		//注意sign转为大写
		System.out.println(sb.toString());
		return MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
	}
	
	/**
	 * 签名工具 不含商户密钥 －暂时不用
	 * @param characterEncoding
	 *        编码格式 UTF-8
	 * @param parameters
	 * @return
	 */
	public static String createSignNoKey(String characterEncoding,
			Map<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Entry <String,Object>entry = (Entry<String,Object>) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();//去掉带sign的项
			if (null != value && !"".equals(value) && !"sign".equals(key)
					&& !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		String signStr = sb.toString();
		String subStr = signStr.substring(0, signStr.length()-1);
		//注意sign转为大写
		return MD5Util.MD5Encode(subStr, characterEncoding).toUpperCase();
	}

	/**
	 * @author zxw
	 * @date
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String,Object> entry = (Entry<String,Object>) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key)
					|| "sign".equalsIgnoreCase(key)) {
				sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
			} else {
				sb.append("<" + key + ">" + value + "</" + key + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * @author zxw
	 * @date
	 * @Description：返回给微信的参数
	 * @param return_code
	 *            返回编码
	 * @param return_msg
	 *            返回信息
	 * @return
	 */
	public static String setXML(String return_code, String return_msg) {
		return "<xml><return_code><![CDATA[" + return_code
				+ "]]></return_code><return_msg><![CDATA[" + return_msg
				+ "]]></return_msg></xml>";
	}
	
	
	/**
     * 从API返回的XML数据里面重新计算一次签名
     *
     * @param responseString API返回的XML数据
     * @return 新鲜出炉的签名
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
	 * @throws JDOMException 
     */
    public static String getSignFromResponseString(String responseString) throws IOException, SAXException, ParserConfigurationException, JDOMException {
    	SortedMap<String, Object> map = XMLUtil.doXMLParse(responseString);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign", "");
       // map.remove("sign");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        return PayCommonUtil.createSign("UTF-8", map);
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     *
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) {

        try {
        	SortedMap<String, Object> map = XMLUtil.doXMLParse(responseString);
            //Map<String, Object> map = XMLParser.getMapFromXML(responseString);
            log.debug(map.toString());
            String signFromAPIResponse = map.get("sign").toString();
            if ("".equals(signFromAPIResponse) || signFromAPIResponse == null) {
                log.debug("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
                return false;
            }
            log.debug("服务器回包里面的签名是:" + signFromAPIResponse);
            //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
            map.put("sign", "");
            //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
            String signForAPIResponse = createSign("UTF-8", map);
            if (!signForAPIResponse.equals(signFromAPIResponse)) {
                //签名验不过，表示这个API返回的数据有可能已经被篡改了
                log.debug("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
                return false;
            }
            log.debug("恭喜，API返回的数据签名验证通过!!!");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
