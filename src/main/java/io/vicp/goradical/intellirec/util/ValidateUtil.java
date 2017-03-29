package io.vicp.goradical.intellirec.util;

import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;
import io.vicp.goradical.intellirec.model.security.Right;
import io.vicp.goradical.intellirec.struts2.action.BaseAction;
import io.vicp.goradical.intellirec.struts2.action.user.UserVoAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/3.
 *
 * 校验工具类
 */
public class ValidateUtil {
	/**
	 * 判断字符串有效性
	 * @param src
	 * @return
	 */
	public static boolean isValid(String src) {
		if (src == null || "".equals(src.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 判断集合的有效性
	 * @param coll
	 * @return
	 */
	public static boolean isValid(Collection coll) {
		if (coll == null || coll.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断Map的有效性
	 * @param map
	 * @return
	 */
	public static boolean isValid(Map map) {
		if (map == null || map.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断数组是否有效
	 * @param arr
	 * @return
	 */
	public static boolean isValid(Object[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		return true;
	}
	public static boolean hasRight(String namespace, String actionName, HttpServletRequest request, BaseAction action) {
		if (!ValidateUtil.isValid(namespace) || "/".equals(namespace)) {
			namespace = "";
		}
		//将超链接参数部分过滤掉
		if (actionName.contains("?")) {
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = namespace + "/" + actionName;
		HttpSession session = request.getSession();
		ServletContext servletContext = session.getServletContext();
		Map<String, Right> map = (Map<String, Right>) servletContext.getAttribute("all_rights_map");
		Right right = map.get(url);
		if (right == null || right.isCommon()) {
			return true;
		} else {
			UserVo userVo = (UserVo) session.getAttribute("userVo");
			//是否登录
			if (userVo == null) {
				return false;
			} else {
				//userAware处理
				if (action != null && action instanceof UserVoAware) {
					((UserVoAware) action).setUser(userVo);
				}
				if (userVo.isSuperAdmin()) {
					return true;
				} else {
					//有权限吗？
					if (userVo.hasRight(right)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}
}
