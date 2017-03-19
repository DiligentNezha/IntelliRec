package io.vicp.goradical.intellirec.service;

import io.vicp.goradical.intellirec.model.pmrs.User;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface UserService extends BaseService<User>{
	/**
	 * 根据用户邮箱和密码进行验证登陆
	 * @param email 邮箱
	 * @param password 密码
	 * @return 用户视图模型
	 */
	UserVo login(String email, String password);

	/**
	 * 根据用户视图模型返回用户数据的数据表格模型
	 * @param userVo
	 * @return 用户数据的数据表格模型
	 */
	TableVo<UserVo> tableData(UserVo userVo);
}
