package io.vicp.goradical.intellirec.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/3/21.
 */
public class ObjectUtil {

	public static String[] getNullField(Object object) {
		List<String> fields = new ArrayList<>();
		Class<?> clz = object.getClass();
		Field[] declaredFields = clz.getDeclaredFields();
		for (Field df : declaredFields) {
			Class<?> type = df.getType();
			if (type != Boolean.class) {
				try {
					Method method = clz.getDeclaredMethod("get" + StringUtil.toUpCaseFirstChar(df.getName()));
					method.setAccessible(true);
					Object value = method.invoke(object);
					if (value == null) {
						fields.add(df.getName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String[] strs = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			strs[i] = fields.get(i);
		}
		return strs;
	}
}
