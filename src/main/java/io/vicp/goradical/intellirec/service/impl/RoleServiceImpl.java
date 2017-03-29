package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.RoleDao;
import io.vicp.goradical.intellirec.model.security.Role;
import io.vicp.goradical.intellirec.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kenny on 2017/3/6.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Autowired
	private RoleDao roleDao;
}