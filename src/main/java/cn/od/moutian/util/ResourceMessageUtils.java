package cn.od.moutian.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class ResourceMessageUtils {
	private static ReloadableResourceBundleMessageSource res;
	static {
		res=new ReloadableResourceBundleMessageSource();
		res.setBasenames("classpath:public_msg");
		res.setDefaultEncoding("utf-8");
		res.setCacheSeconds(1800);
	}
	
	public static boolean validateSplitDate(String reskey,String value,String split)
	{
		String sf=getMessage(reskey, "");
		if(StringUtils.isBlank(sf)) return false;
		if(StringUtils.isBlank(split)) split="\\,";
		String [] array=sf.split(split);
		boolean flg=false;
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(value))
			{
				flg=true;
				break;
			}
		}
		return flg;
	}
	public static String getMessage(String key,String defaultV)
	{
		return res.getMessage(key,null,defaultV,Locale.ENGLISH);
	}
}
