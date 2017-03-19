package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.UserDao;
import io.vicp.goradical.intellirec.model.pmrs.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User login(String email, String password) {
		String hql = "from User u where u.email = :email and u.password = :password";
		HashMap<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		User user = getFirstEntity(hql, params);
		return user;
	}

}
