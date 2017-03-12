package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.CollectRecord;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface CollectionRecordDao extends BaseDao<CollectRecord> {

	/**
	 * 根据视频id统计视频总的收藏量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalCollectWithVideoId(Serializable videoId);
}
