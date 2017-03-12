package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.UserRecommend;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface UserRecommendDao extends BaseDao<UserRecommend> {

	/**
	 * 根据视频id统计视频总的 推荐/不推荐 数量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalUserRecommendWithVideoId(Serializable videoId);
}
