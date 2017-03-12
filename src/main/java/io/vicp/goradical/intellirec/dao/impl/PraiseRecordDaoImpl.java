package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.PraiseRecordDao;
import io.vicp.goradical.intellirec.model.pmrs.PraiseRecord;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("praiseRecordDao")
public class PraiseRecordDaoImpl extends BaseDaoImpl<PraiseRecord> implements PraiseRecordDao {

	@Override
	public long countTotalPraiseWithVideoId(Serializable videoId) {
		String hql = "select count(*) from PraiseRecord cr where cr.video.id = :videoId";
		Map<String, Object> params = new HashMap<>();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}
}
