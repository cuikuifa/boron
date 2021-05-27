package cn.od.moutian.util;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RSAUtils3 {
	 
//								  C4B5D2CE97055F523BAEDA058743C6A01F69BF744C5612B295CAD3A8EF4A445E4855D9FB589AFD67B3618E1140CBFC66BA4EEA81D2C4851DEC13531D2818530D63DAA6A098D7F188C82E7EF442D42DB473C7C5461827D556C7096C12A7CC5CC5E8087F8A86314BF50CDCDF7DCEDFBBF96026DB9CBCF1D7E002C9E21F8499D431
		static final String Modulus= "98D14A3CF6B977EDDD934255523B0C7455F9C4BE1F8217E714EC096D59F197CE896B162B43FE5133E1AAD2FBB5628B311816E5C0E757E6C50A7F590CCB92D1E666EA50E71BD903A14C2ECB637FD7CC358EF70AA2A4E4C1B52736A3E34E653066C47830238ED8EF64F52B166F1498756D30CCD4F003D47129E4BA18516CABF371";
		//"119013762783552790664444510344126639271924844701717173872088828066827617618352052423868797764101050671675010273300968086768952758222793093337410679638385758438181998828779151354457120185810439862646172407875625351933121940976586295594801107405021633366112226209939925716039327701639315656827458274941425368677";
		static final String Exponent= "010001";//"65537";
//		static final String privateE = "106917532045172255415411675710221707032269514269165514577919371366747681689703456650559215972034323501923156458960095779126430300336072236497450160586990533670480101946578693218455238274718168302034205034895891150713831453664198422986491449327196528842768860543017467669252798863303270185486892719101442835713";
		
		/**
		 * 通过指数和模进行加密
	     */
	    public static String encrypt(String content, String Modulus,String Exponent) throws Exception {
	    	// 获取公私钥n
	    	BigInteger N = new BigInteger(Modulus,16);
	    	// 获取公钥e
	    	BigInteger E = new BigInteger(Exponent,16);
//	    	BigInteger testByteInt = new BigInteger(content.getBytes());
	    	int n =  ((N.bitLength() + 7) >> 3);
//		    System.out.println("N.bitLength():"+N.bitLength()+",n:"+n);
		    BigInteger testByteInt = pkcs1pad2(content,n);
//		   					 485233d61fd42e52062b60e9650347bcb194074af3f7c53083558f85734e9f49d6d7c41a1048c804fb7e79f4998dd7323af31d206d90545bf0b01fd274cb9bbd0c6ccabf3117cd0f278123930cbd2a48119394970a5958432bf38b5c5d7978f4da8f3243e2c958a720c602edda8006ceac82e7f76f1a8e2ee34e57855e19fcdc
//		    String sourBy = "1f5357a197beaa169e02560234cd2dccb00318c16f310890c482a3cbbcf5e200e44fa8bfd0ce634d3ec060fbf43bc441ebaeaed97a18d9b35cccc1fece64dddd8323935b0bac3ec9ae943fe7cb7207c0ac43ef1486ffa1d263af444ee1fd78dcfde37563d852126634c0fe59976f7b7b44d77b298ab8d09a73f2599079409582";
		    String targetStr = testByteInt.modPow(E, N).toString(16);
		    if((targetStr.length() & 1) != 0){
		    	targetStr =("0"+targetStr);
		    }
//		    System.out.println("1.targetStr:"+targetStr);
//		    targetStr = b16to64(targetStr);
//		    System.out.println("2.targetStr:"+targetStr);
		    targetStr = b16to642(targetStr);
//		    System.out.println("3.targetStr:"+targetStr);
		    return targetStr;
//	    	return testByteInt.modPow(E, N).toByteArray();//将明文加密为密文
	    }
		
	    public static String encrypt3(String content, String Modulus,String Exponent) throws Exception {
	    	// 获取公私钥n
	    	BigInteger N = new BigInteger(Modulus,16);
	    	// 获取公钥e
	    	BigInteger E = new BigInteger(Exponent,16);
	    	BigInteger testByteInt = new BigInteger(content.getBytes());
//	    	int n =  ((N.bitLength() + 7) >> 3);
//		    System.out.println("N.bitLength():"+N.bitLength()+",n:"+n);
//		    BigInteger testByteInt = pkcs1pad2(content,n);
//		    String targetStr = testByteInt.modPow(E, N).toString(16);
//		    if((targetStr.length() & 1) != 0){
//		    	targetStr =("0"+targetStr);
//		    }
//		    targetStr = b16to64(targetStr);
//		    return targetStr;
	    	return testByteInt.modPow(E, N).toString(16);//将明文加密为密文
	    }
	    
	    
		public static void main(String[] args) throws Exception{
			String VIPARA =  "G7dVpEqtnQSLuHvr";//"ShfPtx30C1TEDsUx";//"WeidL5Lh6daYNCVM";
		    String key = "TpRE2yvwkHZFPjyknUwgM1rvrpG3T218";//"RirWFjXP8Eq8sGVQghoedcHKyxoFIcx7";//"YNfHlDuC6q6lMsNBH0CtX6d7mVCTsICm";
		    String keyivs = key + "|" + VIPARA;
//		    TpRE2yvwkHZFPjyknUwgM1rvrpG3T218|G7dVpEqtnQSLuHvr
			String enctyptByte = encrypt(keyivs, Modulus, Exponent);
//			System.out.println("enctyptByte:"+enctyptByte);
//			String targetData = b16to64(enctyptByte);////Base64.getEncoder().encodeToString(enctyptByte.getBytes());
//			System.out.println("targetData:"+targetData);
			
//			String enctyptByte2 = encrypt2(keyivs, Modulus, Exponent);
//			String targetData2 = Base64.getEncoder().encodeToString(enctyptByte2.getBytes());
//			System.out.println("targetData2:"+targetData2);
			
//			System.out.println("enctyptByte3:"+b16to64(String.valueOf(enctyptByte)));
//							 pnQIRFMfKrunBOwhKM/2AziDCS6KTPEtpj1NGV5jsRUnwZ+ytUZN+/o1S77PDvP9M9Fb8lRu/0eB4hMDhZpPQq+yVQ8ieFsGEUJO2KGzOWwn04ZUp6rsc9JWmLWNPb/e/QhD/jKPmDCR7qgCdCryh9FkQ4VlPwVd6VsCIHEC7K4=
//							 ezE4XJSoC9eYGCE9J3tnsCA/AK1e9baPjNSGnSfv/yWWrvAdW3hFM3qmAmTYj2adA8UUUcO4XHu6ITp+YlesqR9sq9u3L25fVRYELDqG6LBuzzBSozj/5MS1jKbfcP/Myo1orcDpqN0OQUQ2J5SoN3BSAg6AH52E3BTsL/1CeCo=
			String WZdata = "ezE4XJSoC9eYGCE9J3tnsCA%2FAK1e9baPjNSGnSfv%2FyWWrvAdW3hFM3qmAmTYj2adA8UUUcO4XHu6ITp%2BYlesqR9sq9u3L25fVRYELDqG6LBuzzBSozj%2F5MS1jKbfcP%2FMyo1orcDpqN0OQUQ2J5SoN3BSAg6AH52E3BTsL%2F1CeCo%3D";
			WZdata = URLDecoder.decode(WZdata, "utf-8");
//			System.out.println("比较:"+(WZdata.equals(targetData)));
			
//			AIEWSkAj4Q6G8HNWZhGkimvEy1GMx5z3f+X5gFG0m247FAqCS4GWPpGD8ubLjpEywMqHG13uS9Bibo/DwAKXcsOtBy3R1Lpkwls3ahdyEDrjcy+k0M4xHLA3L5wdlLZs8opQMBEN787DC23o3R5lODxzouzEZOSESLVrov4TONEr
//			KLyUnTLYOOAlgp2DHzyco8a1asIhH6HpQeSnHdn/SnPWbXIjlnDeyUgNJxfgPmxLgqQl0H1W3TaA64XzCaWKy9tvkQEWStyndyaxOhdgB2G6TsiN8NIVgpbR7iBw6Cym85wCj7dmREe9KfxGX3Zys5lxuFhj7MTCcFjGrljFU4Y=
//			KLyUnTLYOOAlgp2DHzyco8a1asIhH6HpQeSnHdn/SnPWbXIjlnDeyUgNJxfgPmxLgqQl0H1W3TaA64XzCaWKy9tvkQEWStyndyaxOhdgB2G6TsiN8NIVgpbR7iBw6Cym85wCj7dmREe9KfxGX3Zys5lxuFhj7MTCcFjGrljFU4Y=

//			GbfmL3xNQdUcmwBUSXJpAcQRS1hXK29fmhyW3G9nAQpPa9SxAbLyMCebwkqXsrWaEXU8R8+X1vc7xDWQXolG0kJxxZpLHZ2/2Gz2ZQZOqmUJOZgWmTSMsMGLJpJ2ZuH5AXZbrkTAp5alNuxDTcBFGmqdSkspBqN0nSLO+7T/6K0=
//			b2Va/o4Tpd3+AkoX+iCqoNykSONRPvSG5YcqjGCiZ+aODmeHamMV5JiS8eRZ1mATs9ybI3ez1QPC6SZZPHauIkG1mL/feCzdD+7NeXnTR3V8+GoOohnINzECs1I8ELRTprfrIVMlGUb/0Sl4owmoNg4O3tR7JHbNNsuekyBNG0Y=
//			网站的:
//			YN9zfs22COrQ3SNBa6hS5QXLjLhEHG4nC8KQzaVaXbNISQX+ttHj5B9V44HdGSr+ujyXrqn5MMbgx81xVq+3zimOYFRB/a1q12NSwtU4x6gNECb0I3IXMuXWbAOo3rktyJ8foZgCNvIf9y6yZFq7/ViEvMo/vFr5ybJlSNpwJFE=
//			我们生成的
//			GbfmL3xNQdUcmwBUSXJpAcQRS1hXK29fmhyW3G9nAQpPa9SxAbLyMCebwkqXsrWaEXU8R8+X1vc7xDWQXolG0kJxxZpLHZ2/2Gz2ZQZOqmUJOZgWmTSMsMGLJpJ2ZuH5AXZbrkTAp5alNuxDTcBFGmqdSkspBqN0nSLO+7T/6K0=
//			// 获取公私钥n
//			BigInteger m = new BigInteger(mod,16);
//			// 获取公钥e
//			BigInteger pubE = new BigInteger(publicE);
//			// 密文
//			BigInteger priE = new BigInteger(privateE);
//			String test = "我123这个是测试";
//			test = URLEncoder.encode(test,"UTF-8");//解决中文乱码的问题
//			byte[] testByte = test.getBytes();
//			BigInteger testByteInt = new BigInteger(testByte);
//			
//			byte[] enTestByte = testByteInt.modPow(pubE, m).toByteArray();//将明文加密为密文
//			System.out.println("enTestByte:"+new String(enTestByte));
//			byte[] deTestByte = new BigInteger(enTestByte).modPow(priE, m).toByteArray();//将密文解密为明文
//			System.out.println(URLDecoder.decode(new String(deTestByte), "UTF-8"));
			
			String str = "";
			b16to642(str);

		}

		static String b16to642(String str) throws Exception {
			String result = "";
			 ScriptEngineManager sem = new ScriptEngineManager();
			 ScriptEngine se = sem.getEngineByName("javascript");
			 se.eval(" var base64Chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'; "
			 		+ "function b16to64(h) {\r\n" + 
			 		"		var i;\r\n" + 
			 		"		var c;\r\n" + 
			 		"		var ret = \"\";\r\n" + 
			 		"		if(h.length % 2 == 1)\r\n" + 
			 		"		{\r\n" + 
			 		"			h = \"0\" + h;\r\n" + 
			 		"		}\r\n" + 
			 		"		for (i = 0; i + 3 <= h.length; i += 3)\r\n" + 
			 		"		{\r\n" + 
			 		"			c = parseInt(h.substring(i, i + 3), 16);\r\n" + 
			 		"			ret += base64Chars.charAt(c >> 6) + base64Chars.charAt(c & 63);\r\n" + 
			 		"		}\r\n" + 
			 		"		if (i + 1 == h.length)\r\n" + 
			 		"		{\r\n" + 
			 		"			c = parseInt(h.substring(i, i + 1), 16);\r\n" + 
			 		"			ret += base64Chars.charAt(c << 2);\r\n" + 
			 		"		}\r\n" + 
			 		"		else if (i + 2 == h.length)\r\n" + 
			 		"		{\r\n" + 
			 		"			c = parseInt(h.substring(i, i + 2), 16);\r\n" + 
			 		"			ret += base64Chars.charAt(c >> 2) + base64Chars.charAt((c & 3) << 4);\r\n" + 
			 		"		}\r\n" + 
			 		"		while ((ret.length & 3) > 0) ret += \"=\";\r\n" + 
			 		"		return ret;\r\n" + 
			 		"} "
			 		+ " var test = b16to64(\""+str+"\");");
			 
				result = se.get("test").toString();
				System.out.println(result);
				return result;
		}
		
		 private static BigInteger pkcs1pad2(String s, int n) {
		        if (n < s.length() + 11) { 
		            System.err.println("Message too long for RSA");
		            return null;
		        }
		        byte[] ba = new byte[n];
		        int i = s.length() - 1;
		        while (i >= 0 && n > 0) {
		            int c = s.codePointAt(i--);
		            if (c < 128) { // encode using utf-8
		                ba[--n] = new Byte(String.valueOf(c));
		            } else if ((c > 127) && (c < 2048)) {
		                ba[--n] = new Byte(String.valueOf((c & 63) | 128));
		                ba[--n] = new Byte(String.valueOf((c >> 6) | 192));
		            } else {
		                ba[--n] = new Byte(String.valueOf((c & 63) | 128));
		                ba[--n] = new Byte(String.valueOf(((c >> 6) & 63) | 128));
		                ba[--n] = new Byte(String.valueOf((c >> 12) | 224));
		            }
		        }
		        ba[--n] = new Byte("0");

		        byte[] temp = new byte[1];
		        Random rdm = new Random(47L);

		        while (n > 2) { // random non-zero pad
		            temp[0] = new Byte("0");
		            while (temp[0] == 0)
		                rdm.nextBytes(temp);
		            ba[--n] = temp[0];
		        }
		        ba[--n] = 2;
		        ba[--n] = 0;

		        return new BigInteger(ba);
		    }

		
		static String b16to64(String h) {
			String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
			int i;
			int c;
			String ret = "";
			if(h.length() % 2 == 1)
			{
				h = "0" + h;
			}
			for (i = 0; (i + 3) <= h.length(); i += 3)
			{
				c = Integer.parseInt(h.substring(i, i + 3), 16);
				ret += base64Chars.charAt(c >> 6) + base64Chars.charAt(c & 63);
			}
			if (i + 1 == h.length())
			{
				c = Integer.parseInt(h.substring(i, i + 1), 16);
				ret += base64Chars.charAt(c << 2);
			}
			else if (i + 2 == h.length())
			{
				c = Integer.parseInt(h.substring(i, i + 2), 16);
				ret += base64Chars.charAt(c >> 2) + base64Chars.charAt((c & 3) << 4);
			}
			while ((ret.length() & 3) > 0) ret += "=";
			return ret;
		}
		
		static void generateKeyPair(){
			KeyPairGenerator keyPairGen = null;
			try {
				keyPairGen = KeyPairGenerator.getInstance("RSA");
			} catch (NoSuchAlgorithmException e) {
			}
			keyPairGen.initialize(1024);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			System.out.println("publicKey Mod: "+publicKey.getModulus());
			System.out.println("publicKey Exp: "+publicKey.getPublicExponent());
			System.out.println("privateKey Mod: "+privateKey.getModulus());
			System.out.println("privateKey Exp: "+privateKey.getPrivateExponent());
		}
		

		static String encrypt2(String text,String Modulus,String Exponent){
			// 获取公私钥n
	    	BigInteger n = new BigInteger(Modulus,16);
	    	// 获取公钥e
	    	BigInteger e = new BigInteger(Exponent,16);
	    	
			BigInteger m = pkcs1pad2(text, ((n.bitLength() + 7) >> 3));
			if (m == null) return null;
			BigInteger c = m.modPow(e, n);
			if (c == null) return null;
			String h = c.toString(16);
			if ((h.length() & 1) == 0) return h;
			else return "0" + h;
		}
	
		
}
