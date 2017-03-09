package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.UserDao;
import io.vicp.goradical.intellirec.model.pmrs.User;
import io.vicp.goradical.intellirec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kenny on 2017/3/6.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

}
