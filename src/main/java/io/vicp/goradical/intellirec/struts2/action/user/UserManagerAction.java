package io.vicp.goradical.intellirec.struts2.action.user;

import com.alibaba.fastjson.JSON;
import io.vicp.goradical.intellirec.model.pmrs.User;
import io.vicp.goradical.intellirec.model.pmrs.vo.JsonVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;
import io.vicp.goradical.intellirec.service.UserService;
import io.vicp.goradical.intellirec.struts2.action.BaseAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kenny on 2017/3/15.
 */
@Namespace("/userManager")
@Action(value = "userManagerAction")
public class UserManagerAction extends BaseAction<UserVo> {
	private static final Logger LOG = LogManager.getLogger(UserManagerAction.class);

	@Autowired
	private UserService us;

	public void tableData() {
		TableVo<UserVo> userVoTableVo = us.tableData(model);
		super.writeJson(userVoTableVo);
	}

	@Action(value = "userManagerAction_toUserInfoManagerPage", results = {
			@Result(name = SUCCESS, location = "/background/user/user-info-manager.jsp")})
	public String toUserInfoManagerPage() {
		TableVo<UserVo> userVoTableVo = us.tableData(model);
		String jsonStr = JSON.toJSONStringWithDateFormat(userVoTableVo, "yyyy-MM-dd HH:mm:ss");
		getRequest().setAttribute("jsonStr", jsonStr);
		return SUCCESS;
	}

	public void getUser() {
		UserVo userVo = us.getUser(model.getId());
		super.writeJson(userVo);
	}

	public void deleteUser() {
		us.deleteEntity(model.getId());
	}

	public void updateUser() {
		us.updateUser(model);
		JsonVo jsonVo = new JsonVo();
		jsonVo.setSuccess(true);
		super.writeJson(jsonVo);
	}

	public void addUser() {
		User user = new User();
		BeanUtils.copyProperties(model, user);
		us.saveEntity(user);
	}
}
