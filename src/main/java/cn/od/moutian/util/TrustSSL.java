package cn.od.moutian.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.Header;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * https 请求工具类
 * 
 */
@Component
@Scope("prototype")
public class TrustSSL {
	public static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * 获得一个keyStore
	 * 
	 * @throws Exception
	 */
	private static KeyStore getKeyStore(String keyStorePath, String password)
			throws Exception {
		// 获得密钥库文件输入流
		FileInputStream is = new FileInputStream(keyStorePath);

		// 实例化密钥库
		KeyStore ks = KeyStore.getInstance("JKS");

		// 加载密钥库
		ks.load(is, password.toCharArray());
		is.close();
		return ks;
	}

	/**
	 * 获得SSLSocketFactory
	 * 
	 * @return
	 */
	private static SSLSocketFactory getSSLSocketFactory(String password,
			String keyStorePath, String trustKeyStorePath) throws Exception {
		// 初始化密钥
		KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance("SunX509");

		KeyStore keyStore = getKeyStore(keyStorePath, password);
		keyManagerFactory.init(keyStore, password.toCharArray());

		// 初始化化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance("SunX509");
		KeyStore trustKeyStore = getKeyStore(trustKeyStorePath, password);
		trustManagerFactory.init(trustKeyStore);

		// 初始化SSL Context
		SSLContext ctx = SSLContext.getInstance("SSL"); // STL
		ctx.init(keyManagerFactory.getKeyManagers(),
				trustManagerFactory.getTrustManagers(), null);
		SSLSocketFactory sf = ctx.getSocketFactory();
		return sf;
	}

	private static void testHttps() throws Exception {
		String password = "12345";
		String keyStorePath = "G:" + File.separator + "luguozhu"
				+ File.separator + "ssl" + File.separator + "keystore";// "client.crt";
		String trustKeyStorePath = keyStorePath;

		java.io.File file = new java.io.File(keyStorePath);
		System.out.println("path:" + file.getAbsolutePath());

		// 构建URL对象
		URL url = new URL("https://www.baidu.com/");
		// 获得HttpsURLConnection
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		// 打开输入模式
		conn.setDoInput(true);
		// 打开输出模式
		conn.setDoOutput(true);

		conn.setSSLSocketFactory(getSSLSocketFactory(password, keyStorePath,
				trustKeyStorePath));
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

		InputStream is = conn.getInputStream();
		int length = conn.getContentLength();
		System.out.println("length:" + length);
		is.close();
	}

	private CertPath getCertificateFactory() {
		try {
			// 实例化CertificateFactory对象，并指明证书类型为 "X.509"
			CertificateFactory certificateFactory = CertificateFactory
					.getInstance("X.509");
			// 获得证书输入流
			FileInputStream in = new FileInputStream(
					"G:\\luguozhu\\ssl\\root.crt");
			// 获得CertPath对象
			CertPath certPath = certificateFactory.generateCertPath(in);
			// 关闭流
			in.close();
			return certPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String url = "https://dev.10086.cn/wabps/wap/purchase.action?apco=300009025599&aptid=13801022946&aptrid=161024005649626IXCA_ps_ec3be6a9ea2e44ad9a8e5cbf0acfd1d3"
				+ "&bu=aHR0cDovL24uM2d0di5uZXQvZ21wcEJ1eS93YWJwX29yZGVyX3Jlc3VsdC5qc3A/VHJhbnNhY3Rpb25JRD0xNjEwMjQwMDU2"
				+ "NDk2MjZJWENBX3BzX2VjM2JlNmE5ZWEyZTQ0YWQ5YThlNWNiZjBhY2ZkMWQzJlNlcnZpY2VDb2RlPTMwMDAwOTAyNTU5OSZNU0lT"
				+ "RE49TVRNNE1ERXdNakk1TkRZPSZwbXNfaWQ9MCZwbXNfY29udGVudFR5cGU9MCZzaWduPUNBMjVBRjFEMTk2NjQ4MjAwRTA3OEZDRDY0RTNBMjU5QkIyRDM5NDJFMzhFNkY0MDU4Nzg0NjY4QzlDMTcxMjY"
				+ "=&ch=31575&ex=94001&mid=IBFRIKAFRZZOHE&sin=zdyyf&xid=null&sign=MC0CFCwg3YQDa1p/DOKgB2/aUIOLN1quAhUAjICnsn4ApMbf5f3eG3iTA0vdPNo=";
		// "https://www.baidu.com";
		Map<String, String> paramMap = new HashMap<String, String>();
		// paramMap.put("key1", "aa");
		// paramMap.put("key2", "bb");
//		List<Header> resultMap = httpsUrl(url, paramMap, paramMap, "", 30000,30000, "");
//		for (Header header : resultMap) {
//			System.out.println(header.getName() + ":" + header.getValue());
//		}
		// System.out.println(resultMap.toString());

		// testHttps();

	}
	
	public static SSLContext sc = null;

	/**
	 * https 请求
	 * 
	 * @param url
	 * @param paramMap
	 * @param connectTimeout
	 * @param readTimeout
	 * @param method
	 *            Set the method for the URL request, one of:
	 *            <UL>
	 *            <LI>GET
	 *            <LI>POST
	 *            <LI>HEAD
	 *            <LI>OPTIONS
	 *            <LI>PUT
	 *            <LI>DELETE
	 *            <LI>TRACE
	 *            </UL>
	 *            are legal, subject to protocol restrictions. The default
	 *            method is GET.
	 * @return
	 */
	public List<Header> httpsUrl(String url,
			Map<String, String> headMap, Map<String, String> paramMap,
			String data, int connectTimeout, int readTimeout, String method) {

		Assert.notNull(url, "url not null");
		List<Header> list = new ArrayList<Header>();
		Header contentHead = new Header();
		contentHead.setName("resultContent");

		byte[] buffer = new byte[1024];

		try {

//			if(sc== null){
			SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());
//			}
			
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();

			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			if (StringUtils.isNotBlank(method))
				conn.setRequestMethod(method);

			// set req param
			if (paramMap != null && paramMap.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String key : paramMap.keySet()) {
					sb.append("&").append(key).append("=").append(paramMap.get(key));
				}
				if (url.indexOf("?") != -1) {
					url = url + sb.toString();
				} else {
					url = url + "?" + sb.substring(1, sb.length());
				}
			}

			// set header
			if (headMap != null && headMap.size() > 0) {
				for (String key : headMap.keySet()) {
					conn.setRequestProperty(key, headMap.get(key));
				}
			}

			conn.setDoInput(true);
			if("POST".equals(method)){
				conn.setDoOutput(true);
				OutputStream out = conn.getOutputStream();
				
				// set data
				if (StringUtils.isNotBlank(data)) {
//					conn.setRequestProperty("Content-Length",String.valueOf(data.getBytes().length));
					out.write(data.getBytes("utf-8"));
				}
				
				out.flush();
				out.close();
				
			}else {
				conn.connect();	
			}
			
			conn.connect();	
			

			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);

			// 获取响应码
			Header codeHead = new Header();
			codeHead.setName("statusCode");
			codeHead.setValue(String.valueOf(conn.getResponseCode()));
			list.add(codeHead);

			// 获取响应状态msg
			Header msgHead = new Header();
			msgHead.setName("statusMsg");
			msgHead.setValue(conn.getResponseMessage());
			list.add(msgHead);

			StringBuilder sb = new StringBuilder();
			int len = 0;
			while ((len = indata.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}
			contentHead.setValue(sb.toString());
			list.add(contentHead);

			// get response header
			Map<String, List<String>> map2 = conn.getHeaderFields();
			for (String key : map2.keySet()) {
				for (String val : map2.get(key)) {
					Header header = new Header();
					header.setName(key);
					header.setValue(val);
					list.add(header);
				}
			}

			// 关闭连接
			conn.disconnect();

		} catch (ConnectException ce) {
			ce.printStackTrace();
			contentHead
					.setValue(String.format("ConnectException[url:%s]", url));
			list.add(contentHead);

		} catch (IOException ie) {
			ie.printStackTrace();
			contentHead.setValue(String.format("IOException[url:%s]", url));
			list.add(contentHead);
		} catch (Exception e) {
			e.printStackTrace();
			contentHead.setValue(String.format("Exception[url:%s]", url));
			list.add(contentHead);
		} finally {

		}
		return list;
	}
	
	/**
	 * https 请求
	 * 
	 * @param url
	 * @param paramMap
	 * @param connectTimeout
	 * @param readTimeout
	 * @param method
	 *            Set the method for the URL request, one of:
	 *            <UL>
	 *            <LI>GET
	 *            <LI>POST
	 *            <LI>HEAD
	 *            <LI>OPTIONS
	 *            <LI>PUT
	 *            <LI>DELETE
	 *            <LI>TRACE
	 *            </UL>
	 *            are legal, subject to protocol restrictions. The default
	 *            method is GET.
	 * @return
	 */
	public List<Header> httpsUrl(String url,
			Map<String, String> headMap, Map<String, String> paramMap,
			String data, int connectTimeout, int readTimeout, String method,SSLContext sc) {

		Assert.notNull(url, "url not null");
		List<Header> list = new ArrayList<Header>();
		Header contentHead = new Header();
		contentHead.setName("resultContent");

		byte[] buffer = new byte[1024];

		try {
			
			if(sc== null)
			{
				sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());
			}
			
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();

			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			if (StringUtils.isNotBlank(method))
				conn.setRequestMethod(method);

			// set req param
			if (paramMap != null && paramMap.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String key : paramMap.keySet()) {
					sb.append("&").append(key).append("=").append(paramMap.get(key));
				}
				if (url.indexOf("?") != -1) {
					url = url + sb.toString();
				} else {
					url = url + "?" + sb.substring(1, sb.length());
				}
			}

			// set header
			if (headMap != null && headMap.size() > 0) {
				for (String key : headMap.keySet()) {
					conn.setRequestProperty(key, headMap.get(key));
				}
			}

			conn.setDoInput(true);
			if("POST".equals(method)){
				conn.setDoOutput(true);
				OutputStream out = conn.getOutputStream();
				// set data
				if (StringUtils.isNotBlank(data)) {
//					conn.setRequestProperty("Content-Length",String.valueOf(data.getBytes().length));
					out.write(data.getBytes("utf-8"));
				}
				out.flush();
				out.close();
			}
			conn.connect();	
			

			InputStream is = conn.getInputStream();
			DataInputStream indata = new DataInputStream(is);

			// 获取响应码
			Header codeHead = new Header();
			codeHead.setName("statusCode");
			codeHead.setValue(String.valueOf(conn.getResponseCode()));
			list.add(codeHead);

			// 获取响应状态msg
			Header msgHead = new Header();
			msgHead.setName("statusMsg");
			msgHead.setValue(conn.getResponseMessage());
			list.add(msgHead);

			StringBuilder sb = new StringBuilder();
			int len = 0;
			while ((len = indata.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len));
			}
			contentHead.setValue(sb.toString());
			list.add(contentHead);

			// get response header
			Map<String, List<String>> map2 = conn.getHeaderFields();
			for (String key : map2.keySet()) {
				for (String val : map2.get(key)) {
					Header header = new Header();
					header.setName(key);
					header.setValue(val);
					list.add(header);
				}
			}

			// 关闭连接
			conn.disconnect();

		} catch (ConnectException ce) {
			ce.printStackTrace();
			contentHead
					.setValue(String.format("ConnectException[url:%s]", url));
			list.add(contentHead);

		} catch (IOException ie) {
			ie.printStackTrace();
			contentHead.setValue(String.format("IOException[url:%s]", url));
			list.add(contentHead);
		} catch (Exception e) {
			e.printStackTrace();
			contentHead.setValue(String.format("Exception[url:%s]", url));
			list.add(contentHead);
		} finally {

		}
		return list;
	}
	
	
	public List<Header> httpsUrlDownload(String url,
			Map<String, String> headMap, Map<String, String> paramMap,
			String data, int connectTimeout, int readTimeout, 
			String method,SSLContext sc,String filePath,String fileName) {

		Assert.notNull(url, "url not null");
		List<Header> list = new ArrayList<Header>();
		Header contentHead = new Header();
		contentHead.setName("resultContent");

		byte[] buffer = new byte[1024];

		try {
			
			if(sc== null)
			{
				sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());
			}
			
			URL console = new URL(url);
			HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();

			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			if (StringUtils.isNotBlank(method))
				conn.setRequestMethod(method);

			// set req param
			if (paramMap != null && paramMap.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String key : paramMap.keySet()) {
					sb.append("&").append(key).append("=").append(paramMap.get(key));
				}
				if (url.indexOf("?") != -1) {
					url = url + sb.toString();
				} else {
					url = url + "?" + sb.substring(1, sb.length());
				}
			}

			// set header
			if (headMap != null && headMap.size() > 0) {
				for (String key : headMap.keySet()) {
					conn.setRequestProperty(key, headMap.get(key));
				}
			}

			conn.setDoInput(true);
			conn.connect();	
			

			InputStream is = conn.getInputStream();
//			DataInputStream indata = new DataInputStream(is);

			// 获取响应码
			Header codeHead = new Header();
			codeHead.setName("statusCode");
			codeHead.setValue(String.valueOf(conn.getResponseCode()));
			list.add(codeHead);

			// 获取响应状态msg
			Header msgHead = new Header();
			msgHead.setName("statusMsg");
			msgHead.setValue(conn.getResponseMessage());
			list.add(msgHead);

			 byte[] bs = new byte[1024]; 
			 int len; 
			 OutputStream os = new FileOutputStream(filePath+fileName);
			// 开始读取  
		        while ((len = is.read(bs)) != -1) {  
		          os.write(bs, 0, len);  
		        }  
		        // 完毕，关闭所有链接  
		        os.close();  
		        is.close();
//			StringBuilder sb = new StringBuilder();
//			int len = 0;
//			while ((len = indata.read(buffer)) != -1) {
//				sb.append(new String(buffer, 0, len));
//			}
//			contentHead.setValue(sb.toString());
		    contentHead.setValue(fileName);
			list.add(contentHead);

			// get response header
			Map<String, List<String>> map2 = conn.getHeaderFields();
			for (String key : map2.keySet()) {
				for (String val : map2.get(key)) {
					Header header = new Header();
					header.setName(key);
					header.setValue(val);
					list.add(header);
				}
			}

			
			// 关闭连接
			conn.disconnect();
			
		} catch (ConnectException ce) {
			ce.printStackTrace();
			contentHead.setValue(String.format("ConnectException[url:%s]", url));
			list.add(contentHead);

		} catch (IOException ie) {
			ie.printStackTrace();
			contentHead.setValue(String.format("IOException[url:%s]", url));
			list.add(contentHead);
		} catch (Exception e) {
			e.printStackTrace();
			contentHead.setValue(String.format("Exception[url:%s]", url));
			list.add(contentHead);
		} finally {

		}
		return list;
	}
	
}