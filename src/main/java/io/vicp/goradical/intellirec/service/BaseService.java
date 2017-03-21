package io.vicp.goradical.intellirec.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/3.
 *
 * 提供对 BaseDao 的基本封装及一些自定义的基础业务方法
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * 根据实体 id 检索实体对象
	 *
	 * @param id 序列化 id
	 * @return 实体对象
	 */
	T loadEntity(Serializable id);

	/**
	 * 保存实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 * @return 对象保存成功后的对象序列化 id
	 */
	Serializable saveEntity(T t);

	/**
	 * 保存/更新实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	void saveOrUpdateEntity(T t);

	/**
	 * 更新实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	void updateEntity(T t);

	/**
	 * 实体对象的 merge 操作,详细说明参阅官方文档
	 *
	 * @param t 泛型化的实体对象 t
	 * @return 实体对象
	 */
	T mergeEntity(T t);

	/**
	 * 持久化实体对象 t
	 *
	 * @param t 泛型化的实体对象 t
	 */
	void persistEntity(T t);

	/**
	 * 删除实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	void deleteEntity(T t);

	/**
	 * 根据对象id删除实体对象
	 * @param id 序列化实体id
	 */
	void deleteEntity(Serializable id);

	/**
	 * 刷新实体对象
	 *
	 * @param t 泛型化的实体对象 t
	 */
	void refreshEntity(T t);

	/**
	 * 获取实体对象
	 *
	 * @param id
	 * @return
	 */
	T getEntity(Serializable id);

	// 下边的方法是自定方法接口，主要是跟 Query 有关的操作

	/**
	 * 通过 Query 批量操作实体对象
	 *
	 * @param hql hql 语句
	 */
	int batchEntityByHQL(String hql);

	/**
	 * 通过 Query 批量操作实体对象
	 *
	 * @param hql     hql 语句
	 * @param objects Object对象数组,通过参数位置方式给 hql 语句添加参数,不推荐使用
	 */
	int batchEntityByHQL(String hql, Object... objects);

	/**
	 * 批量操作实体对象
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 */
	int batchEntityByHQL(String hql, Map<String, Object> params);

	//执行原生SQL语句

	/**
	 * 通过 Query 执行原生 sql 语句
	 *
	 * @param sql 原生 sql 语句
	 * @return 语句执行后受影响的数据行数
	 */
	int executeSQL(String sql);

	/**
	 * 通过 Query 执行原生 sql 语句
	 *
	 * @param sql     原生 sql 语句
	 * @param objects Object对象数组,通过参数位置方式给 sql 语句添加参数
	 * @return
	 */
	int executeSQL(String sql, Object... objects);

	//读操作

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql hql 语句
	 * @return 实体对象List
	 */
	List<T> findEntityByHQL(String hql);

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql  hql 语句
	 * @param page 要检索数据的页数
	 * @param rows 每页的数据行数
	 * @return 实体对象 List
	 */
	List<T> findEntityByHQL(String hql, int page, int rows);

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql     hql 语句
	 * @param objects Object对象数组, 通过参数位置方式给 hql 语句添加参数,不推荐使用
	 * @return 实体对象 List
	 */
	List<T> findEntityByHQL(String hql, Object... objects);

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @return 实体对象 List
	 */
	List<T> findEntityByHQL(String hql, Map<String, Object> params);

	/**
	 * 通过 Query 检索实体对象
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @param page   要检索数据的页数
	 * @param rows   每页的数据行数
	 * @return 实体对象 List
	 */
	List<T> findEntityByHQL(String hql, Map<String, Object> params, int page, int rows);

	//单值检索，确保查询结果有且只有一条记录

	/**
	 * 通过 Query 执行单值检索,如聚合函数 sum(), count(), 要求 hql 语句执行后返回数据最多只有一条
	 *
	 * @param hql hql 语句
	 * @return 单值检索得到 Object 对象
	 */
	Object uniqueResult(String hql);

	/**
	 * 通过 Query 执行单值检索,如聚合函数 sum(), count(), 要求 hql 语句执行后返回数据最多只有一条
	 *
	 * @param hql     hql 语句
	 * @param objects Object 对象数据,通过参数位置方式给 hql 语句添加参数,不推荐使用
	 * @return 单值检索得到的 Object 对象
	 */
	Object uniqueResult(String hql, Object... objects);

	/**
	 * 通过 Query 执行单值检索,如聚合函数 sum(), count(), 要求 hql 语句执行后返回数据最多只有一条
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @return 单值检索得到的 Object 对象
	 */
	Object uniqueResult(String hql, Map<String, Object> params);

	//执行原生查询,可以指定是否封装成实体

	/**
	 * 执行原生 SQL 检索
	 *
	 * @param clazz 要检索的类对象,为空则表示不封装成实体
	 * @param sql   sql 语句
	 * @return List 对象
	 */
	List executeSQLQuery(Class clazz, String sql);

	/**
	 * 执行原生 SQL 检索
	 *
	 * @param clazz   要检索的类对象,为空则表示不封装成实体
	 * @param sql     sql 语句
	 * @param objects Object 对象数据,通过参数位置方式给 sql 语句添加参数
	 * @return List 对象
	 */
	List executeSQLQuery(Class clazz, String sql, Object... objects);

	/**
	 * 得到检索结果的第一条数据
	 *
	 * @param hql hql 语句
	 * @return 实体对象
	 */
	T getFirstEntity(String hql);

	/**
	 * 得到检索结果的第一条数据
	 *
	 * @param hql     hql 语句
	 * @param objects Object 对象数组,通过参数位置方式给 hql 语句添加参数,不推荐使用
	 * @return 实体对象
	 */
	T getFirstEntity(String hql, Object... objects);

	/**
	 * 得到检索结果的第一条数据
	 *
	 * @param hql    hql 语句
	 * @param params Map 对象,通过参数名方式给 hql 语句添加参数,推荐使用
	 * @return 实体对象
	 */
	T getFirstEntity(String hql, Map<String, Object> params);



	//查询所有实体
	List<T> findAllEntities();

}
