package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.GlanceRecord;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface GlanceRecordDao extends BaseDao<GlanceRecord> {
	/**
	 * 根据视频id统计视频总的浏览量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalGlanceWithVideoId(Serializable videoId);
}
