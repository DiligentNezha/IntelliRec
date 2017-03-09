package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.UserDao;
import io.vicp.goradical.intellirec.model.pmrs.CommentRecord;
import io.vicp.goradical.intellirec.model.pmrs.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/6.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public Set<CommentRecord> getCommentRecords(Serializable id) {
		User user = getEntity(id);
		Set<CommentRecord> commentRecords = user.getCommentRecords();
		commentRecords.size();
		return commentRecords;
	}
}
