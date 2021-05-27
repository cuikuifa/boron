package cn.od.moutian.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密工具类
*/ 
public class AESUtil {
	public static final String VIPARA = "WeidL5Lh6daYNCVM";
    public static final String bm ="UTF-8";
    public static final String key = "YNfHlDuC6q6lMsNBH0CtX6d7mVCTsICm";
    
    public static String getRandomString(Integer len){
//        len = len == null ? 32 : len;
        String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = code.length();
        String result = "";
        for (int i = 0; i < len; i++) {
            result += code.charAt((int)Math.floor(Math.random() * length));
        }
        return result;
    }
    
	/**
	 * 加密
	 * @param crealtext
	 * @param password
	 * @return
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 */
	public static String encrypt(String cleartext) 
			throws Exception{
		String dataPassword = key;
		IvParameterSpec zeroIv =  new  IvParameterSpec(VIPARA.getBytes());  
	    SecretKeySpec key =  new  SecretKeySpec(dataPassword.getBytes(),  "AES" );  
	    Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS7Padding" );  
	    cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);  
	    byte [] encryptedData = cipher.doFinal(cleartext.getBytes(bm));  
	    return  Base64.encode(encryptedData).replace(" ", "");   
	}
	
	/**
	 * 加密
	 */
	public static String encrypt(String cleartext,String dataPassword,String VIPARA) 
			throws Exception{
		IvParameterSpec zeroIv =  new  IvParameterSpec(VIPARA.getBytes());  
	    SecretKeySpec key =  new  SecretKeySpec(dataPassword.getBytes(),  "AES" );  
	    Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS7Padding" );  
	    cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);  
	    byte [] encryptedData = cipher.doFinal(cleartext.getBytes(bm));  
	    return  Base64.encode(encryptedData).replace(" ", "");   
	}
	
	
	/**
	 * 解密
	 * @param decryptStr
	 * @param password
	 * @return
	 */
	public static String decrypt(String encrypted)
			throws Exception{
			String dataPassword = key;
		 	byte [] byteMi = Base64.decode(encrypted);  
		    IvParameterSpec zeroIv =  new  IvParameterSpec(VIPARA.getBytes());  
		    SecretKeySpec key =  new  SecretKeySpec(dataPassword.getBytes(),  "AES" );  
		    Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS7Padding" );  
		    cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);  
		    byte [] decryptedData = cipher.doFinal(byteMi);  
		    return new String(decryptedData,bm); 
	}
	
	/**
	 * 解密
	 */
	public static String decrypt(String encrypted,String dataPassword,String VIPARA)
			throws Exception{
		 	byte [] byteMi = Base64.decode(encrypted);  
		    IvParameterSpec zeroIv =  new  IvParameterSpec(VIPARA.getBytes());  
		    SecretKeySpec key =  new  SecretKeySpec(dataPassword.getBytes(),  "AES" );  
		    Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS7Padding" );  
		    cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);  
		    byte [] decryptedData = cipher.doFinal(byteMi);  
		    return new String(decryptedData,bm); 
	}
	
	public static void main(String[] args) throws Exception {
//		String key = "cEl5wraOL0RbQ5VPFXZjcB7qwtQ3B1xZkPlN90scQFpu6fp0yiGtz6IWl1GwAGtLTbPFYKPlygysMYJLXwiuGB8h9xID7tKuygHUhtJ4W0cYwbfFuelXbyTVXsPBoZkbdgNEoOJexNh%2FSeV1zjpbSwPrd%2FULRvBgv1VZnWSi%2B0I%3D";
//		key = URLDecoder.decode(key, "UTF-8");
//		System.out.println("key:"+key+"\nleng:"+key.length());
//		curl 'https://e.abchina.com/site/Account/CheckLogin' -X POST -H 'Accept: */*' -H 'Accept-Encoding: gzip, deflate, br' 
//		-H 'Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3' 
//		-H 'Content-Length: 125' -H 'Content-Type: application/json;charset=UTF-8'
//		-H 'Cookie: BIGipServerpool_ebiz_9000=1911352074.10275.0000; EbizSessionId=ss2m0adqt45xkacajzjvg4ne' 
//		-H 'Host: e.abchina.com' -H 'Referer: https://e.abchina.com/emall/view/login/login.html' 
//		-H 'User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0' -H 'X-EncryptType: Aes' 
//		-H 'X-IntegrityHash: 8le7tMrIHCrQPY+lrNuLLoH6nSY=' -H 'X-Requested-With: XMLHttpRequest' -H 'X-Timestamp: 1536746418'
		
//		-H 'X-IntegrityHash: 8le7tMrIHCrQPY+lrNuLLoH6nSY='
		
		String minStr = "{\"LoginName\":\"HTCashier1\",\"Password\":\"20180803ht\",\"ValidateCode\":\"zkz2\"}";
		String miStr = encrypt(minStr);
		System.out.println("miStr:"+miStr);
//							 EdmknmLhrjI7S1avDSusZ6QcucWfao60v%2FY45vSZcs5hAWEGzZixlFOOaV5Klh4iHdbZLMM0EwSEMbCzCApoRtdJA%2BSK71itrDN2hp0JmbdoFcnTDcLBibQB0zoTanTvStN1xjHdkBdqO4Cvvf4hrbbKgXm62tfSS1lbvoxyd%2B4%3D
		String encryptMsg = "jaRoNcGy8UnlLHoCWx2tbwg0nKpmfa928r5VDLubWxSkQCwCpuyuUrz0VuTF4+qMnN7zTSLobT1jd4jwgBuRTZQPAgNsyeTtyeFl/Vkcur0=";
		String jieStr = decrypt(encryptMsg);
		System.out.println("jieStr:"+jieStr);
	}
}
