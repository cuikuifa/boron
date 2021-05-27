package cn.od.moutian.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * RSA 工具
 * 用openssl生成512位RSA：
 * 生成私钥：
 * openssl genrsa -out key.pem 512
 * 从私钥中导出公钥：
 * openssl rsa -in key.pem -pubout -out public-key.pem
 * 公钥加密：
 * openssl rsautl -encrypt -in xx.file -inkey public-key.pem -pubin -out xx.en
 * 私钥解密：
 * openssl rsautl -decrypt -in xx.en -inkey key.pem -out xx.de
 * 要在Java内调用还要进行pkcs8编码：
 * openssl pkcs8 -topk8 -inform PEM -in key.pem -outform PEM -out private-key.pem -nocrypt
 * 最后将公私玥放在/resources/rsa/：private-key.pem public-key.pem
 *
 * @author Zoctan
 * @date 2018/05/27
 */
@Component
@Slf4j
public class RSAUtil {

    private final String algorithm = "RSA";

    /**
     * 生成密钥对
     *
     * @param keyLength 密钥长度(最少512位)
     * @return 密钥对
     * 公钥 keyPair.getPublic()
     * 私钥 keyPair.getPrivate()
     * @throws Exception e
     */
    public KeyPair genKeyPair(final int keyLength) throws Exception {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(this.algorithm);
        keyPairGenerator.initialize(keyLength);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 公钥加密
     *
     * @param content   待加密数据
     * @param publicKey 公钥
     * @return 加密内容
     * @throws Exception e
     */
    public byte[] encrypt(final byte[] content, final PublicKey publicKey) throws Exception {
        final Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    /**
     * 私钥解密
     *
     * @param content    加密数据
     * @param privateKey 私钥
     * @return 解密内容
     * @throws Exception e
     */
    public byte[] decrypt(final byte[] content, final PrivateKey privateKey) throws Exception {
        final Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 私钥加密
     *
     * @param content   待加密数据
     * @param privateKey 私钥
     * @return 加密内容
     * @throws Exception e
     */
    public byte[] encrypt(final byte[] content, final PrivateKey privateKey) throws Exception {
        final Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * 公钥解密
     *
     * @param content    加密数据
     * @param publicKey 公钥
     * @return 解密内容
     * @throws Exception e
     */
    public byte[] decrypt(final byte[] content, final PublicKey publicKey) throws Exception {
        final Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }


    /*
     * 根据路径读取文件，pem文件中存在头尾字符串分割，原本的方法中会对此2段字符串进行分割，但是Windows系统中的换行符为\n，linux下的换行符为\t\n即环境不同时会出现
     * 无法正常分割，导致字符串过长的问题。暂时使用手动去除头尾字符串的办法解决这个问题，工具方法不在使用分割代码
     * */
    private byte[] replaceAndBase64Decode(final String file/*, final String headReplace, final String tailReplace*/) throws Exception {
        final ResourceLoader loader = new DefaultResourceLoader();
        final Resource resource = loader.getResource(file);
        String tempPath = System.getProperty("java.io.tmpdir") + System.currentTimeMillis() + ".pem";
        File f = new File(tempPath);
        IOUtils.copy(resource.getInputStream(), new FileOutputStream(f));
        final FileInputStream fis = new FileInputStream(f);
        final DataInputStream dis = new DataInputStream(fis);
        final byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        final String temp = new String(keyBytes);
//        String KeyPEM = temp.replace(headReplace, "");
//        KeyPEM = KeyPEM.replace(tailReplace, "");
        return Base64.decodeBase64(temp);
    }

    /**
     * 加载pem格式的公钥
     *
     * @param pem 公钥文件名
     * @return 公钥
     */
    public PublicKey loadPemPublicKey(final String pem) {
        try {
            final byte[] decoded = this.replaceAndBase64Decode(
                    pem/*,
                    "-----BEGIN PUBLIC KEY-----\n",
                    "-----END PUBLIC KEY-----"*/
            );
            final X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            final KeyFactory keyFactory = KeyFactory.getInstance(this.algorithm);
            return keyFactory.generatePublic(spec);
        } catch (final Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 加载pem格式PKCS8编码的私钥
     *
     * @param pem 私钥文件名
     * @return 私钥
     */
    public PrivateKey loadPemPrivateKey(final String pem) {
        try {
            final byte[] decoded = this.replaceAndBase64Decode(
                    pem/*,
                    "-----BEGIN PRIVATE KEY-----\n",
                    "-----END PRIVATE KEY-----"*/
            );
            final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
            final KeyFactory keyFactory = KeyFactory.getInstance(this.algorithm);
            return keyFactory.generatePrivate(spec);
        } catch (final Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 加载字符串公钥
     *
     * @param publicKey 公钥字符串
     * @return 公钥
     */
    public PublicKey getPublicKey(final String publicKey) throws Exception{
        byte[] decoded = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    /**
     * 加载字符串私钥
     *
     * @param privateKey 私钥字符串
     * @return 公钥
     */
    public PrivateKey getPrivateKey(final String privateKey) throws Exception{
        byte[] decoded = Base64.decodeBase64(privateKey);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        final KeyFactory keyFactory = KeyFactory.getInstance(this.algorithm);
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 用公钥签名
     * explain : 首先new一个sortedMap,这个map生成的时候会自动排序的,然后将这个map转成一个把所有键值对以“&”字符连接起来的字符串,
     * 然后将这个字符串进行SHA1withRSA签名获得byte[] sign, 然后再进行String result = Base64.encodeBase64String(sign);再将此字符串放入原来的params中.
     *
     * @param params
     * @param keyStr
     * @throws Exception
     */
    // todo 此函数似乎有bug，但还没有进行调试
    public void signWithKey(Map<String, String> params, String keyStr) throws Exception {
        TreeMap<String, String> sortedMap = new TreeMap<String, String>(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> param : sortedMap.entrySet()) {
            String value = param.getValue();
            if (!isBlank(value)) {
                sb.append(param.getValue());
            }
        }
        try {
            // 数据源byte数组
            byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
            byte[] sign = encrypt(bytes, getPublicKey(keyStr));
            // 加密后的字符串
            params.put("sign", Base64Util.encode(sign));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("签名字符串信息出错,原因为{}", e.getMessage());
            throw e;
        }
    }

    /**
     * 用于返回信息的验签，用私钥解密
     *
     * @param params
     * @param priKey
     * @return
     */
    public boolean checkParams(Map<String, String> params, String priKey) {
        TreeMap<String, String> sortedMap = new TreeMap<String, String>(params);
        String sign = sortedMap.get("sign") + "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> param : sortedMap.entrySet()) {
            String key = param.getKey();
            // 对map⾥的key排除“sign”后从a到z的顺序排序，再把所有键值对以“&”字符连接起来（值为空的字段，请勿拼接）
            if (!"sign".equals(key)) {
                if (!isBlank(String.valueOf(param.getValue()))) {
                    sb.append(String.valueOf(param.getValue()));
                }
            }
        }
//      使用去掉换行符的我方公钥和返回报⽂中sign字段的对拼接后的字符串进行SHA1WithRSA验签。
        try {
            // 签名字符串的byte数组
            byte[] bytes = Base64Util.decode(sign);
            // 拿到解密byte数组
            PrivateKey privateKey = getPrivateKey(priKey);
            byte[] source = decrypt(bytes, privateKey);
            return sb.toString().equals(new String(source));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("验签结果失败,原因为{}", e.getMessage());
        }
        return false;
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }



}