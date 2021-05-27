package cn.od.moutian.api.pay;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jacky on 16/9/8.
 * 上海渠道方构建post的提交数据
 */
public class MkEntity {

    public static HttpEntity mkEntity(RequestParam param, String signKey) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        param.setSign(SignUtil.signFromObject(param, signKey));
        System.out.println("param.getSign()" + param.getSign());
        Field[] fields = param.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object object = null;
            try {
                object = field.get(param);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("解析参数出错");
            }
            if (object != null && !"".equalsIgnoreCase(object.toString().trim())) {

                pairs.add(new BasicNameValuePair(field.getName(), object.toString()));
            }
        }

        try {
            return new UrlEncodedFormEntity(pairs, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpEntity mkEntity(Map param) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Object key : param.keySet()) {
            pairs.add(new BasicNameValuePair((String) key, (String)param.get(key)));
        }
        try {
            return new UrlEncodedFormEntity(pairs, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
