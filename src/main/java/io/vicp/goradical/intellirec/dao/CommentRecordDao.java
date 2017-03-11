package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.CommentRecord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface CommentRecordDao extends BaseDao<CommentRecord> {
	/**
	 * 根据视频id统计视频总的评论量
	 * @param videoId 视频id
	 * @return 统计总数
	 */
	long countTotalCommentWithVideoId(Serializable videoId);

	/**
	 * 根据视频id获取所有评论此视频的用户Id
	 * @param videoId 视频id
	 * @return 用户id集合
	 */
	Set<Integer> getUserIdsWithVideoId(Serializable videoId);

	/**
	 * 根据视频id获取所有评论记录
	 * @param videoId 视频id
	 * @return 评论记录集合
	 */
	List getCommentRecordList(Serializable videoId);

	/**
	 * 获取给定日期之后的评论记录
	 * @param date 给定日期
	 * @return 评论记录集合
	 */
	List getCommentRecordList(Date date);
}
