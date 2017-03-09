package io.vicp.goradical.intellirec.tools;

import java.util.List;

public class ListTool {
	public static String convertListToString(List<Integer> list) {
		String result = list.toString();
		result = "(" + result.substring(1, result.length() - 1) + ")";
		return result;
	}
}
