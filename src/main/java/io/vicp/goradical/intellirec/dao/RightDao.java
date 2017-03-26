package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.security.Right;

import java.util.List;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/22.
 */
public interface RightDao extends BaseDao<Right> {
	/**
	 * 保存/更新权限
	 */
	void saveOrUpdateRight(Right model);

	/**
	 * 按照url追加权限
	 */
	void appendRightByURL(String url);

	/**
	 * 批量更新权限
	 */
	void batchUpdateRights(List<Right> allRights);

	/**
	 * 查询在指定范围内的权限
	 */
	List<Right> findRightsInRange(Integer[] ids);

	/**
	 * 查询不在指定范围内的权限
	 */
	List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * 查询最大权限位
	 */
	int getMaxRightPos();
}
