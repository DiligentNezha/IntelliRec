package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.BaseDao;
import io.vicp.goradical.intellirec.util.ValidateUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/3.
 * <p>
 * BaseDao 的实现，专门用于继承
 * <p>
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	/**
	 * sessionFactory 注入
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 获取泛型类实例的类信息
	 */
	protected Class<T> clazz;

	/**
	 * 默认构造器，通过反射获取泛型类实例的类信息并赋值给clazz
	 */
	public BaseDaoImpl() {
		//得到泛型化的超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}


	/**
	 * 根据实体 id 检索实体对象
	 *
	 * @param id 序列化 id
	 * @return 实体对象
	 */
	@Override
	public T loadEntity(Serializable id) {
		return (T) getCurrentSession().load(clazz, id);
	}

	/**
	 * 保存实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 * @return 对象保存成功后的对象序列化 id
	 */
	@Override
	public Serializable saveEntity(T t) {
		return getCurrentSession().save(t);
	}

	/**
	 * 保存/更新实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	@Override
	public void saveOrUpdateEntity(T t) {
		getCurrentSession().saveOrUpdate(t);
	}

	/**
	 * 更新实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	@Override
	public void updateEntity(T t) {
		getCurrentSession().update(t);
	}

	/**
	 * 实体对象的 merge 操作,详细说明参阅官方文档
	 *
	 * @param t 泛型化的实体对象 t
	 * @return 实体对象
	 */
	@Override
	public T mergeEntity(T t) {
		return (T) getCurrentSession().merge(t);
	}

	/**
	 * 持久化实体对象 t
	 *
	 * @param t 泛型化的实体对象 t
	 */
	@Override
	public void persistEntity(T t) {
		getCurrentSession().persist(t);
	}

	/**
	 * 删除实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	@Override
	public void deleteEntity(T t) {
		getCurrentSession().delete(t);
	}

	/**
	 * 刷新实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	@Override
	public void refreshEntity(T t) {
		getCurrentSession().refresh(t);
	}

	/**
	 * 获取实体对象
	 *
	 * @param id
	 * @return
	 */
	@Override
	public T getEntity(Serializable id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	/**
	 * 通过 Query 批量操作实体对象
	 *
	 * @param hql hql 语句
	 */
	@Override
	public int batchEntityByHQL(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	/**
	 * 通过 Query 批量操作实体对象
	 *
	 * @param hql     hql 语句
	 * @param objects Object对象数组,通过参数位置方式给 hql 语句添加参数,不推荐使用
	 */
	@Override
	public int batchEntityByHQL(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(q, objects);
		return q.executeUpdate();
	}

	/**
	 * 批量操作实体对象
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 */
	@Override
	public int batchEntityByHQL(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(params, q);
		return q.executeUpdate();
	}

	/**
	 * 通过 Query 执行原生 sql 语句
	 *
	 * @param sql 原生 sql 语句
	 * @return 语句执行后受影响的数据行数
	 */
	@Override
	public int executeSQL(String sql) {
		SQLQuery sq = getCurrentSession().createSQLQuery(sql);
		return sq.executeUpdate();
	}

	/**
	 * 通过 Query 执行原生 sql 语句
	 *
	 * @param sql     原生 sql 语句
	 * @param objects Object对象数组,通过参数位置方式给 sql 语句添加参数
	 * @return
	 */
	@Override
	public int executeSQL(String sql, Object... objects) {
		SQLQuery sq = sessionFactory.getCurrentSession().createSQLQuery(sql);
		Validate(sq, objects);
		return sq.executeUpdate();
	}

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql hql 语句
	 * @return 实体对象List
	 */
	@Override
	public List<T> findEntityByHQL(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql  hql 语句
	 * @param page 要检索数据的页数
	 * @param rows 每页的数据行数
	 * @return 实体对象 List
	 */
	@Override
	public List<T> findEntityByHQL(String hql, int page, int rows) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql     hql 语句
	 * @param objects Object对象数组, 通过参数位置方式给 hql 语句添加参数,不推荐使用
	 * @return 实体对象 List
	 */
	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(q, objects);
		return q.list();
	}

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @return 实体对象 List
	 */
	@Override
	public List<T> findEntityByHQL(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(params, q);
		return q.list();
	}

	@Override
	public List<T> findEntityByHQL(String hql, Map<String, Object> params, int page, int rows) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(params, q);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 通过 Query 执行单值检索,如聚合函数 sum(), count(), 要求 hql 语句执行后返回数据最多只有一条
	 *
	 * @param hql hql 语句
	 * @return 单值检索得到 Object 对象
	 */
	@Override
	public Object uniqueResult(String hql) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.uniqueResult();
	}

	/**
	 * 通过 Query 执行单值检索,如聚合函数 sum(), count(), 要求 hql 语句执行后返回数据最多只有一条
	 *
	 * @param hql     hql 语句
	 * @param objects Object 对象数据,通过参数位置方式给 hql 语句添加参数,不推荐使用
	 * @return 单值检索得到的 Object 对象
	 */
	@Override
	public Object uniqueResult(String hql, Object... objects) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(q, objects);
		return q.uniqueResult();
	}

	/**
	 * 通过 Query 执行单值检索,如聚合函数 sum(), count(), 要求 hql 语句执行后返回数据最多只有一条
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @return 单值检索得到的 Object 对象
	 */
	@Override
	public Object uniqueResult(String hql, Map<String, Object> params) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		Validate(params, q);
		return q.uniqueResult();
	}

	/**
	 * 执行原生 SQL 检索
	 *
	 * @param clazz 要检索的类对象,为空则表示不封装成实体
	 * @param sql   sql 语句
	 * @return List 对象
	 */
	@Override
	public List executeSQLQuery(Class clazz, String sql) {
		SQLQuery sq = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (clazz != null) {
			sq.addEntity(clazz);
		}
		return sq.list();
	}

	/**
	 * 执行原生 SQL 检索
	 *
	 * @param clazz   要检索的类对象,为空则表示不封装成实体
	 * @param sql     sql 语句
	 * @param objects Object 对象数据,通过参数位置方式给 sql 语句添加参数
	 * @return List 对象
	 */
	@Override
	public List executeSQLQuery(Class clazz, String sql, Object... objects) {
		SQLQuery sq = sessionFactory.getCurrentSession().createSQLQuery(sql);
		if (clazz != null) {
			sq.addEntity(clazz);
		}
		Validate(sq, objects);
		return sq.list();
	}

	/**
	 * 得到检索结果的第一条数据
	 *
	 * @param hql hql 语句
	 * @return 实体对象
	 */
	@Override
	public T getFirstEntity(String hql) {
		List<T> list = findEntityByHQL(hql);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 得到检索结果的第一条数据
	 *
	 * @param hql     hql 语句
	 * @param objects Object 对象数组,通过参数位置方式给 hql 语句添加参数,不推荐使用
	 * @return 实体对象
	 */
	@Override
	public T getFirstEntity(String hql, Object... objects) {
		List<T> list = findEntityByHQL(hql, objects);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 得到检索结果的第一条数据
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @return 实体对象
	 */
	@Override
	public T getFirstEntity(String hql, Map<String, Object> params) {
		List<T> list = findEntityByHQL(hql, params);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 获取和当前线程绑定的 Session
	 *
	 * @return
	 */
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 验证数组参数的有效性
	 * @param q Query 对象
	 * @param objects Object 数组对象
	 */
	private void Validate(Query q, Object[] objects) {
		if (ValidateUtil.isValid(objects)) {
			for (int i = 0; i < objects.length; i++) {
				q.setParameter(i, objects[i]);
			}
		}
	}

	/**
	 * 验证 Map 参数的有效性
	 * @param params Map 对象,用来给 Query 添加查询参数
	 * @param q Query 对象
	 */
	private void Validate(Map<String, Object> params, Query q) {
		if (ValidateUtil.isValid(params)) {
			for (String s : params.keySet()) {
				q.setParameter(s, params.get(s));
			}
		}
	}
}
