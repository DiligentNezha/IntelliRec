package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.RightDao;
import io.vicp.goradical.intellirec.dao.UserDao;
import io.vicp.goradical.intellirec.model.pmrs.User;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;
import io.vicp.goradical.intellirec.service.UserService;
import io.vicp.goradical.intellirec.util.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RightDao rightDao;

	@Override
	public UserVo login(String email, String password) {
		User user = userDao.login(email, password);
		UserVo userVo = null;
		if (user != null) {
			//初始化权限总和数组
			int maxPos = rightDao.getMaxRightPos();
			user.setRightSum(new long[maxPos + 1]);
			//计算用户权限总和
			user.calculateRightSum();
			userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);
		}
		return userVo;
	}

	@Override
	public TableVo<UserVo> tableData(UserVo userVo) {
		TableVo<UserVo> userVoTableVo = new TableVo<>();
		String hql = "from User u";
		HashMap<String, Object> params = new HashMap<>();
		hql = addWhere(userVo, hql, params);
		userDao.getCurrentSession().createQuery(hql);
		String countHql = "select count(*) " + hql;
		List<User> userList = userDao.findEntityByHQL(hql, params, userVo.getPage(), userVo.getRows());
		List<UserVo> userVoList = new ArrayList<>(userList.size());
		for (User user : userList) {
			UserVo userVoTemp = new UserVo();
			userVoTemp.setPage(userVo.getPage());
			userVoTemp.setRows(userVo.getRows());
			BeanUtils.copyProperties(user, userVoTemp, "roles");
			userVoList.add(userVoTemp);
		}
		userVoTableVo.setRecordsTotal((Long) userDao.uniqueResult(countHql, params));
		userVoTableVo.setRows(userVoList);
		return userVoTableVo;
	}

	@Override
	public UserVo getUser(Serializable id) {
		User user = getEntity(id);
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user, userVo, "roles");
		return userVo;
	}

	@Override
	public void updateUser(UserVo userVo) {
		User user = getEntity(userVo.getId());
		BeanUtils.copyProperties(userVo, user, ObjectUtil.getNullField(userVo));
		saveOrUpdateEntity(user);
	}

	private String addWhere(UserVo userVo, String hql, Map<String, Object> params) {
		if (userVo.getUserName() != null && !"".equals(userVo.getUserName().trim())) {
			hql += " where u.userName like :name";
			params.put("name", "%%" + userVo.getUserName().trim() + "%%");
		}
		if (userVo.getEmail() != null && !"".equals(userVo.getEmail().trim())) {
			if (userVo.getUserName() == null || "".equals((userVo.getUserName().trim()))) {
				hql += " where u.email like :email";
			} else {
				hql += " and u.email like :email";
			}
			params.put("email", "%%" + userVo.getEmail().trim() + "%%");
		}
		return hql;
	}

}