package cn.od.moutian.api.wxpay;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 
 * @author zxw
 * @desc:信任管理中心
 */
public class FsTrustManager implements X509TrustManager {

	// 检查客户端证书
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 检查服务器端证书
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 返回受信任的X509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
