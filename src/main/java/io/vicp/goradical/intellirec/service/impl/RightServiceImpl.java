package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.RightDao;
import io.vicp.goradical.intellirec.model.security.Right;
import io.vicp.goradical.intellirec.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/6.
 */
@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	@Autowired
	private RightDao rightDao;

	@Override
	public void saveOrUpdateRight(Right right) {
		rightDao.saveOrUpdateEntity(right);
	}

	@Override
	public void appendRightByURL(String url) {
		rightDao.appendRightByURL(url);
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		rightDao.batchUpdateRights(allRights);
	}

	@Override
	public List<Right> findRightsInRange(Integer[] ids) {
		return rightDao.findRightsInRange(ids);
	}

	@Override
	public List<Right> findRightsNotInRange(Set<Right> rights) {
		return rightDao.findRightsNotInRange(rights);
	}

	@Override
	public int getMaxRightPos() {
		return rightDao.getMaxRightPos();
	}
}