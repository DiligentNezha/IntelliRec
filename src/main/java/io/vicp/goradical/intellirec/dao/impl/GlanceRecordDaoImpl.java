package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.GlanceRecordDao;
import io.vicp.goradical.intellirec.model.pmrs.GlanceRecord;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("glanceRecordDao")
public class GlanceRecordDaoImpl extends BaseDaoImpl<GlanceRecord> implements GlanceRecordDao {

	@Override
	public long countTotalGlanceWithVideoId(Serializable videoId) {
		String hql = "select count(*) from GlanceRecord gr where gr.video.id = :videoId";
		HashMap<String, Object> params = new HashMap<>();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}
}
