package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.User;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface UserDao extends BaseDao<User> {
	/**
	 * 根据用户邮箱和密码进行登陆验证
	 * @param email 邮箱
	 * @param password 密码
	 * @return 存在用户返回此用户，否则返回null
	 */
	User login(String email, String password);
}
