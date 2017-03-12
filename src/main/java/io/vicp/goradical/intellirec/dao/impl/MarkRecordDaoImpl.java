package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.MarkRecordDao;
import io.vicp.goradical.intellirec.model.pmrs.MarkRecord;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("markRecordDao")
public class MarkRecordDaoImpl extends BaseDaoImpl<MarkRecord> implements MarkRecordDao{

	@Override
	public long countTotalMarkWithVideoId(Serializable videoId) {
		String hql = "select count(*) from MarkRecord cr where cr.video.id = :videoId";
		Map<String, Object> params = new HashMap<>();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}
}
