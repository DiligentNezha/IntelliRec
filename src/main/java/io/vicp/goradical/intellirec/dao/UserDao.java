package io.vicp.goradical.intellirec.dao;

import io.vicp.goradical.intellirec.model.pmrs.CommentRecord;
import io.vicp.goradical.intellirec.model.pmrs.User;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/6.
 */
public interface UserDao extends BaseDao<User> {
	Set<CommentRecord> getCommentRecords(Serializable id);
}
