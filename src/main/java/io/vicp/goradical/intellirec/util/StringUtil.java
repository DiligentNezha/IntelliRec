package io.vicp.goradical.intellirec.util;

/**
 * Created by Kenny on 2017/3/8.
 */
public class StringUtil {
	/**
	 * 将字符串首字母大写
	 * @param str
	 * @return
	 */
	public static String toUpCaseFirstChar(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
}
