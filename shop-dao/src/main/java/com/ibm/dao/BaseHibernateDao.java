package com.ibm.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.common.dto.PageUtil;
import com.ibm.common.util.ConditionQuery;
import com.ibm.common.util.OrderBy;

public abstract class BaseHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements IBaseDao<M, PK> {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(BaseHibernateDao.class);

	private final Class<M> entityClass;
	private final String HQL_LIST_ALL;
	private final String HQL_COUNT_ALL;
	private final String HQL_OPTIMIZE_PRE_LIST_ALL;
	private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
	private String pkName = null;

	@SuppressWarnings("unchecked")
	public BaseHibernateDao() {
		this.entityClass = (Class<M>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Field[] fields = this.entityClass.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(Id.class)) {
				this.pkName = f.getName();
			}
		}

		HQL_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " order by " + pkName + " desc";
		HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " where " + pkName + " > ? order by " + pkName + " asc";
		HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName()
				+ " where " + pkName + " < ? order by " + pkName + " desc";
		HQL_COUNT_ALL = " select count(*) from "
				+ this.entityClass.getSimpleName();
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 用于保存
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PK save(M model) {
		return (PK) getSession().save(model);
	}

	/**
	 * 用于保存或者修改
	 */
	@Override
	public void saveOrUpdate(M model) {
		getSession().saveOrUpdate(model);
	}

	/**
	 * 用于修改
	 */
	@Override
	public void update(M model) {
		getSession().update(model);

	}

	@Override
	public void merge(M model) {
		getSession().merge(model);
	}

	/**
	 * 根据主键删除
	 */
	@Override
	public void delete(PK id) {
		getSession().delete(this.get(id));

	}

	/**
	 * 根据对象删除
	 */
	@Override
	public void deleteObject(M model) {
		getSession().delete(model);

	}

	/**
	 * 根据ID判断是否存在此对象
	 */
	@Override
	public boolean exists(PK id) {
		return get(id) != null;
	}

	/**
	 * 根据ID获取对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public M get(PK id) {
		return (M) getSession().get(this.entityClass, id);
	}

	/**
	 * 查询所有记录的个数
	 */
	@Override
	public int countAll() {
		Long total = aggregate(HQL_COUNT_ALL);
		return total.intValue();
	}

	/**
	 * 获取所有记录
	 */
	@Override
	public List<M> listAll() {
		return list(HQL_LIST_ALL);
	}

	/**
	 * 分页获取所有记录
	 */
	@Override
	public List<M> listAll(int pn, int pageSize) {
		return this.list(HQL_LIST_ALL, pn, pageSize);
	}

	@Override
	public List<M> pre(PK pk, int pn, int pageSize) {
		if (pk == null) {
			return list(HQL_LIST_ALL, pn, pageSize);
		}
		// 倒序，重排
		List<M> result = listWithParam(HQL_OPTIMIZE_PRE_LIST_ALL, 1, pageSize,
				pk);
		Collections.reverse(result);
		return result;
	}

	@Override
	public List<M> next(PK pk, int pn, int pageSize) {
		if (pk == null) {
			return list(HQL_LIST_ALL, pn, pageSize);
		}
		return listWithParam(HQL_OPTIMIZE_NEXT_LIST_ALL, 1, pageSize, pk);
	}

	/**
	 * 刷新session，将对象强制保存到数据库
	 */
	@Override
	public void flush() {
		getSession().flush();
	}

	/**
	 * 清空session
	 */
	@Override
	public void clear() {
		getSession().clear();
	}

	protected long getIdResult(String hql, Object... paramlist) {
		long result = -1;
		List<?> list = list(hql, paramlist);
		if (list != null && list.size() > 0) {
			return ((Number) list.get(0)).longValue();
		}
		return result;
	}

	protected List<M> listSelf(final String hql, final int pn,
			final int pageSize, final Object... paramlist) {
		return this.<M> list(hql, pn, pageSize, paramlist);
	}

	/**
	 * 根据条件分页查询，但是特殊在于多了一个map，这个是用于对某一个参数的value可能是一个Collection<?>
	 * 
	 * @param hql
	 * @param start
	 * @param length
	 * @param map
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> listWithIn(final String hql, final int start,
			final int length, final Map<String, Collection<?>> map,
			final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		for (Entry<String, Collection<?>> e : map.entrySet()) {
			query.setParameterList(e.getKey(), e.getValue());
		}
		if (start > -1 && length > -1) {
			query.setMaxResults(length);
			if (start != 0) {
				query.setFirstResult(start);
			}
		}
		List<T> results = query.list();
		return results;
	}

	/**
	 * 根据条件进行分页查询
	 * 
	 * @param hql
	 * @param pn
	 * @param pageSize
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> listWithParam(final String hql, final int pn,
			final int pageSize, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		if (pn > -1 && pageSize > -1) {
			query.setMaxResults(pageSize);
			int start = PageUtil.getPageStart(pn, pageSize);
			if (start != 0) {
				query.setFirstResult(start);
			}
		}
		if (pn < 0) {
			query.setFirstResult(0);
		}
		List<T> results = query.list();
		return results;
	}

	/**
	 * 根据查询条件返回唯一一条记录
	 * 
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T unique(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		return (T) query.setMaxResults(1).uniqueResult();
	}

	/**
	 * 聚合查询，如count，sum等
	 * 
	 * @param hql
	 * @param map
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T aggregate(final String hql,
			final Map<String, Collection<?>> map, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		if (paramlist != null) {
			setParameters(query, paramlist);
			for (Entry<String, Collection<?>> e : map.entrySet()) {
				query.setParameterList(e.getKey(), e.getValue());
			}
		}

		return (T) query.uniqueResult();
	}

	/**
	 * 聚合查询，如count，sum等
	 * 
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T aggregate(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);

		return (T) query.uniqueResult();

	}

	/**
	 * 大数据量操作，如批量的save，update 使用HQL
	 * 
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	protected int execteBulk(final String hql, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	/**
	 * 大数据量操作，如批量的save，update 使用原生的SQL
	 * 
	 * @param natvieSQL
	 * @param paramlist
	 * @return
	 */
	protected int execteNativeBulk(final String natvieSQL,
			final Object... paramlist) {
		Query query = getSession().createSQLQuery(natvieSQL);
		setParameters(query, paramlist);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	/**
	 * 根据SQL语句和参数列表获取结果，没有进行分页
	 * 
	 * @param sql
	 * @param paramlist
	 * @return
	 */
	protected <T> List<T> list(final String sql, final Object... paramlist) {
		return listWithParam(sql, -1, -1, paramlist);
	}

	/**
	 * 原生的分页查询，包含多个参数，也包含页码，也每页数量
	 * 
	 * @param nativeSQL
	 * @param pn
	 * @param pageSize
	 * @param entityList
	 * @param scalarList
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> listByNative(final String nativeSQL, final int pn,
			final int pageSize, final List<Entry<String, Class<?>>> entityList,
			final List<Entry<String, Type>> scalarList,
			final Object... paramlist) {

		SQLQuery query = getSession().createSQLQuery(nativeSQL);
		if (entityList != null) {
			for (Entry<String, Class<?>> entity : entityList) {
				query.addEntity(entity.getKey(), entity.getValue());
			}
		}
		if (scalarList != null) {
			for (Entry<String, Type> entity : scalarList) {
				query.addScalar(entity.getKey(), entity.getValue());
			}
		}

		setParameters(query, paramlist);

		if (pn > -1 && pageSize > -1) {
			query.setMaxResults(pageSize);
			int start = PageUtil.getPageStart(pn, pageSize);
			if (start != 0) {
				query.setFirstResult(start);
			}
		}
		List<T> result = query.list();
		return result;
	}

	/**
	 * 原生的SQL查询
	 * 
	 * @param natvieSQL
	 * @param scalarList
	 * @param paramlist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T aggregateByNative(final String natvieSQL,
			final List<Entry<String, Type>> scalarList,
			final Object... paramlist) {
		SQLQuery query = getSession().createSQLQuery(natvieSQL);
		if (scalarList != null) {
			for (Entry<String, Type> entity : scalarList) {
				query.addScalar(entity.getKey(), entity.getValue());
			}
		}

		setParameters(query, paramlist);

		Object result = query.uniqueResult();
		return (T) result;
	}

	/**
	 * 通过criteria设置查询参数，并且进行分页查询
	 * 
	 * @param query
	 * @param orderBy
	 * @param pn
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> list(ConditionQuery query, OrderBy orderBy,
			final int pn, final int pageSize) {
		Criteria criteria = getSession().createCriteria(this.entityClass);
		query.build(criteria);
		orderBy.build(criteria);
		int start = PageUtil.getPageStart(pn, pageSize);
		if (start != 0) {
			criteria.setFirstResult(start);
		}
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public int countList(ConditionQuery query) {
		Criteria criteria = getSession().createCriteria(this.entityClass);
		query.build(criteria);
		return criteria.list().size();
	}

	/**
	 * Criteria查询，参数已经包含在Criteria对象中
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> list(Criteria criteria) {
		return criteria.list();
	}

	/**
	 * Criteria查询单个对象
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T unique(Criteria criteria) {
		return (T) criteria.uniqueResult();
	}

	/**
	 * 离线的Criteria查询，查询多个对象
	 * 
	 * @param criteria
	 * @return
	 */
	public <T> List<T> list(DetachedCriteria criteria) {
		return list(criteria.getExecutableCriteria(getSession()));
	}

	/**
	 * 离线的Criteria查询，查询单个对象
	 * 
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T unique(DetachedCriteria criteria) {
		return (T) unique(criteria.getExecutableCriteria(getSession()));
	}

	/**
	 * 给Query对象设置多个查询参数
	 * 
	 * @param query
	 * @param paramlist
	 */
	protected void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					// 时间函数比较特殊
					query.setTimestamp(i, (Date) paramlist[i]);
				} else {
					query.setParameter(i, paramlist[i]);
				}
			}
		}
	}

}
