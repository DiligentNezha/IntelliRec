package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.PlayRecord;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface PlayRecordDao extends BaseDao<PlayRecord> {
	/**
	 * 根据视频id统计视频总的播放量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalPlayWithVideoId(Serializable videoId);
}
