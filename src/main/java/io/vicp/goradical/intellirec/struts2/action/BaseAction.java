package io.vicp.goradical.intellirec.struts2.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Kenny on 2017/3/3.
 *
 * 抽象Action，专门用于继承
 * @param <T>
 */
@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, Preparable{
	private static final Logger LOG = LogManager.getLogger(BaseAction.class);

	/**
	 * 泛型化模型 model
	 */
	protected T model;

	public BaseAction() {
		try {
			ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel(){
		return model;
	};

	@Override
	public void prepare() throws Exception {

	}

	/**
	 * 获取 HttpServletResponse 对象
	 * @return httpServletResponse
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	/**
	 * 获取 HttpServletRequest 对象
	 * @return httpServletRequest
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	/**
	 * 获取 HttpSession 对象
	 * @return httpSession
	 */
	protected HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	/**
	 * 获取 ContextPath
	 * @return contextPath
	 */
	protected String getContextPath() {
		return ServletActionContext.getRequest().getContextPath();
	}

	/**
	 * 获取 ServletPath
	 * @return servletPath
	 */
	protected String getServletPath() {
		return ServletActionContext.getRequest().getServletPath();
	}

	/**
	 * 将处理的结果以 json 格式返回给前台
	 * @param object 需要格式化的 对象
	 */
	protected void writeJson(Object object) {
		try {
			String jsonStr = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(jsonStr);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}
}
