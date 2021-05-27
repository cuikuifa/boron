package cn.od.moutian.util;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
 
/**
 * HMAC_SHA1 Sign生成器.
 * 
 * 需要apache.commons.codec包
 * 
 */
public class HMAC_SHA1 {
 
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
 
	/**
	 * 使用 HMAC-SHA1 签名方法对data进行签名
	 * 
	 * @param data
	 *            被签名的字符串
	 * @param key
	 *            密钥     
	 * @return 
                      加密后的字符串
	 */
	public static String genHMAC(String data, String key) {
		byte[] result = null;
		try {
			//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称  
			SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
			//生成一个指定 Mac 算法 的 Mac 对象  
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			//用给定密钥初始化 Mac 对象  
			mac.init(signinKey);
			//完成 Mac 操作   
			byte[] rawHmac = mac.doFinal(data.getBytes());
			result = Base64.encodeBase64(rawHmac);
 
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		} catch (InvalidKeyException e) {
			System.err.println(e.getMessage());
		}
		if (null != result) {
			return new String(result);
		} else {
			return null;
		}
	}
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
//		String genHMAC = genHMAC("111", "2222");
//		System.out.println(genHMAC.length()); //28
//		System.out.println(genHMAC);  // O5fviq3DGCB5NrHcl/JP6+xxF6s=
//	
//		String rsaKey = "D58D7AF2D8FE93985A49D5275AC90EF9CD47AC120DEFF1904500F4D614163DC2D9A1BDF258D8988C2967DD3AFAF2A702A256DE0F13E71C5C89D3A5C60BFC481142A18205FB9F5894953B8793E9CCF6507A9241BA39CCAF257F75DE0C5DB6B509C01B1553F0533B6CBE5E2DBFD625F26304D0162C80E563020E6E3E67DEC04343";        
//		rsaKey = rsaKey.substring(0, 16);
//		String temp = new String(Base64.encodeBase64(rsaKey.getBytes()));
//		System.out.println(temp.length());
//		System.out.println(temp);
		
//		String u= "PIzDvZI5h6t0BQYhmhbncXlQ1cxaAhMt";
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String XTimestamp = String.valueOf(timestamp.getTime()).substring(0, 10);
		String u = createUKey();
        String e = "{\"PageSize\":50,\"CurPage\":1}"+XTimestamp;
//        X-IntegrityHash	//        0Em8sHllpfvLfNV3A5b9sQI13tg=
       String genHMAC = genHMAC(e, u);
        System.out.println(genHMAC.length()); 
		System.out.println(genHMAC); 
//		0Em8sHllpfvLfNV3A5b9sQI13tg=
//		String temp2 = createUKey();
//		System.out.println("temp2 = :"+temp2+" , len:"+temp2.length());
	}

	
	static String getXTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String XTimestamp = String.valueOf(timestamp.getTime()).substring(0, 10);
		return XTimestamp;
	}
	
	static String getIntegrityHash(String XTimestamp) {
		String u = createUKey();
        String e = "{\"PageSize\":50,\"CurPage\":1}"+XTimestamp;
        String IntegrityHash = genHMAC(e, u);
		return IntegrityHash;
	}
	
	static String createUKey() {
		  String t = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	       int e = 32;
	        String o = "";
	        int a = t.length();
	        Random r = new Random();
	        for (int n = 0; n < e; n++){
	        	int index = r.nextInt(a);
	        	o += t.charAt(index);
	        }
	        return o;
	}
	
}
