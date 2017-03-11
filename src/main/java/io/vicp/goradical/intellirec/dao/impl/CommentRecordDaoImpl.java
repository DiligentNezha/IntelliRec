package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.CommentRecordDao;
import io.vicp.goradical.intellirec.model.pmrs.CommentRecord;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("commentRecordDao")
public class CommentRecordDaoImpl extends BaseDaoImpl<CommentRecord> implements CommentRecordDao {

	@Override
	public long countTotalCommentWithVideoId(Serializable videoId) {
		String hql = "select count(*) from CommentRecord cr where cr.video.id = :videoId";
		Map<String, Object> params = new HashMap<>();
		params.put("videoId", videoId);
		return (long) uniqueResult(hql, params);
	}

	@Override
	public Set<Integer> getUserIdsWithVideoId(Serializable videoId) {
		String sql = "select tcr.user_comment_record_id from t_comment_record tcr where tcr.video_comment_record_id = ?";
		List userIds = executeSQLQuery(null, sql, videoId);
		return new HashSet<>(userIds);
	}

	@Override
	public List getCommentRecordList(Serializable videoId) {
		String sql = "select tcr.user_comment_record_id, tcr.video_comment_record_id, tcr.comment_date from t_comment_record tcr where tcr.video_comment_record_id = ?";
		List commentRecordList = executeSQLQuery(null, sql, videoId);
		return commentRecordList;
	}

	@Override
	public List getCommentRecordList(Date date) {
		String sql = "select tcr.user_comment_record_id, tcr.video_comment_record_id, tcr.comment_date from t_comment_record tcr where tcr.comment_date > ? order by tcr.comment_date";
		getCurrentSession().createSQLQuery(sql);
		List commentRecordList = executeSQLQuery(null, sql, date);
		return commentRecordList;
	}
}
