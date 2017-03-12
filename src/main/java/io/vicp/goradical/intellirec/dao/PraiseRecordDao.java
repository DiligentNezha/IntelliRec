package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.PraiseRecord;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface PraiseRecordDao extends BaseDao<PraiseRecord> {
	/**
	 * 根据视频id统计视频总的 赞/踩 数量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalPraiseWithVideoId(Serializable videoId);
}
