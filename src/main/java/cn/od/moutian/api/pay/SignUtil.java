package cn.od.moutian.api.pay;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jacky on 16/9/8.
 * 上海渠道方签名类
 */
public class SignUtil {

    public static String signFromObject(RequestParam request, String signKey) {
        request.setSign("");
        Class<? extends RequestParam> clazz = request.getClass();
        Map<String, String> maps = new HashMap<String, String>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object object = null;
            try {
                object = field.get(request);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (object != null && !"".equals(object.toString().trim())) {
                maps.put(field.getName(), object.toString());
            }
        }

        if (maps.size() != 0) {

            Set<String> strings = maps.keySet();
            String[] keys = strings.toArray(new String[strings.size()]);
            Arrays.sort(keys);
            StringBuffer sb = new StringBuffer();
            for (String key : keys) {
                System.out.println(key + ": " + maps.get(key));
                if (!"extend".equals(key)) {
                    sb.append(maps.get(key));
                }
            }
            String line = sb.toString() + signKey;
            String temp = "1测试商品116.22.58.47localhost20105620180821061739http://localhost/api/qrcode/callBack13_0.010.01www.baidu.com15348466599802.00rvval2lp0ulgyltlgtjm59kvnr4w3ayk8fnwsz9oybbwkhko8d06jwuszssfu49p5fmsl9h";
            System.out.println("line" + line);
            System.out.println("temp" + temp);
            System.out.println(line.equals(temp));
            System.out.println("temp签名" + Encrypt.SHA256(temp));
            System.out.println("line签名" + Encrypt.SHA256(line));
            return Encrypt.SHA256(line);
        }
        return null;
    }

}
