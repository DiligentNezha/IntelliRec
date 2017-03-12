
package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.CollectionRecordDao;
import io.vicp.goradical.intellirec.model.pmrs.CollectRecord;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("collectionRecordDao")
public class CollectionRecordDaoImpl extends BaseDaoImpl<CollectRecord> implements CollectionRecordDao {

	@Override
	public long countTotalCollectWithVideoId(Serializable videoId) {
		String hql = "select count(*) from CollectRecord cr where cr.video.id = :videoId";
		Map<String, Object> params = new HashMap();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}
}
