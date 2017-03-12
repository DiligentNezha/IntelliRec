package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.MarkRecord;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface MarkRecordDao extends BaseDao<MarkRecord> {

	/**
	 * 根据视频id统计视频总的评分量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalMarkWithVideoId(Serializable videoId);
}
