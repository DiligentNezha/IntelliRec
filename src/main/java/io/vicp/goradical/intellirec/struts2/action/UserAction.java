package io.vicp.goradical.intellirec.struts2.action;

import com.opensymphony.xwork2.ActionContext;
import io.vicp.goradical.intellirec.model.pmrs.vo.JsonVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;
import io.vicp.goradical.intellirec.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kenny on 2017/3/13.
 */
@Namespace("/user")
@Action(value = "userAction")
public class UserAction extends BaseAction<UserVo> {
	private static final Logger LOG = LogManager.getLogger(UserAction.class);
	@Autowired
	private UserService us;

	public void hello() {
		LOG.info("hello");
	}

	@Action(value = "userAction_login", results = {
			@Result(name = SUCCESS, location = "/background/index.jsp"),
			@Result(name = INPUT, location = "/background/login.jsp")})
	public String login() {
		String email = model.getEmail();
		String password = model.getPassword();
		UserVo userVo = us.login(email, password);
		JsonVo jsonVo = new JsonVo();
		if (userVo != null) {
			jsonVo.setSuccess(true);
			ActionContext.getContext().getSession().put("userVo", userVo);
			jsonVo.setMsg("登陆成功!");
			getSession().setAttribute("userVo", userVo);
			return SUCCESS;
		} else {
			jsonVo.setMsg("用户名或密码错误!");
			return INPUT;
		}
	}


	@Action(value = "userAction_logout", results = {
			@Result(name = SUCCESS, location = "/background/login.jsp")})
	public String logout() {
		getSession().removeAttribute("userVo");
		return SUCCESS;
	}
}
