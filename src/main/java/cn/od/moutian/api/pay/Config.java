package cn.od.moutian.api.pay;

/**
 * Created by jacky on 16/9/14.
 * 上海渠道方配置类
 */
public class Config {
//    private static String ip = "api.99epay.net";
    private static String ip = "115.28.114.220";
    private static String url = "http://" + ip + "/mctrpc/order/mkReceiptOrder.htm";
    @Deprecated
    private static String h5Url = "http://" + ip + "/h5Pay/order/mkH5Order.htm";
    private static String key = "0rvval2lp0ulgyltlgtjm59kvnr4w3ayk8fnwsz9oybbwkhko8d06jwuszssfu49p5fmsl9h";
    private static int connectionTimeout = 10000;
    private static int readTimeout = 10000;

    public static String getH5Url() {
        return h5Url;
    }

    public static void setH5Url(String h5Url) {
        Config.h5Url = h5Url;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Config.url = url;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Config.key = key;
    }

    public static int getConnectionTimeout() {
        return connectionTimeout;
    }

    public static void setConnectionTimeout(int connectionTimeout) {
        Config.connectionTimeout = connectionTimeout;
    }

    public static int getReadTimeout() {
        return readTimeout;
    }

    public static void setReadTimeout(int readTimeout) {
        Config.readTimeout = readTimeout;
    }
}
