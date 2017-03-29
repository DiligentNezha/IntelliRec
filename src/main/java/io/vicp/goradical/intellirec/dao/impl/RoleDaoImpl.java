package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.RoleDao;
import io.vicp.goradical.intellirec.model.security.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

}
