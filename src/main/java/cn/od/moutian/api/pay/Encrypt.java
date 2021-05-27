package cn.od.moutian.api.pay;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Created by jacky on 16/9/8.
 * 上海渠道方sha256加密类
 */
public class Encrypt {

    public static String SHA256(String text) {
        return SHA(text, "SHA-256");
    }

    public static boolean CheckSHA256(String text, String sign) {
        String param = text + Config.getKey();
        return sign.equals(SHA(param, "SHA-256"));
    }

    private static String SHA(String text, String type) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            byte[] bytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {

                String hex = Integer.toHexString(0xff & bytes[i]);
                if (hex.length() == 1) {
                    sb.append("0");
                }
                sb.append(hex);

            }
            result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


}
