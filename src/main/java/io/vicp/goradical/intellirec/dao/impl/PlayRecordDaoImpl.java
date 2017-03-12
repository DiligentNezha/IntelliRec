package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.PlayRecordDao;
import io.vicp.goradical.intellirec.model.pmrs.PlayRecord;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("playRecordDao")
public class PlayRecordDaoImpl extends BaseDaoImpl<PlayRecord> implements PlayRecordDao {
	@Override
	public long countTotalPlayWithVideoId(Serializable videoId) {
		String hql = "select count(*) from PlayRecord cr where cr.video.id = :videoId";
		Map<String, Object> params = new HashMap<>();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}
}
