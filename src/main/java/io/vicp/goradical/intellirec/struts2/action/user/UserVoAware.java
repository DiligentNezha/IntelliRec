package io.vicp.goradical.intellirec.struts2.action.user;


import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;

/**
 * Created by Kenny on 2017/3/3.
 *
 * 用户关注
 */
public interface UserVoAware {
	void setUser(UserVo userVo);
}
