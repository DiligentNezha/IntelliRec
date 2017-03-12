package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.UserRecommendDao;
import io.vicp.goradical.intellirec.model.pmrs.UserRecommend;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("userRecommendDao")
public class UserRecommendDaoImpl extends BaseDaoImpl<UserRecommend> implements UserRecommendDao {

	@Override
	public long countTotalUserRecommendWithVideoId(Serializable videoId) {
		String hql = "select count(*) from UserRecommend cr where cr.video.id = :videoId";
		Map<String, Object> params = new HashMap<>();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}
}
