package com.ibm.dao;

import java.util.List;

/**
 * 接口类 包含了基本操作
 * 
 * @author david
 * 
 * @param <M>  模型
 * @param <PK> 主键
 */
public interface IBaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {

	public PK save(M model);

	public void saveOrUpdate(M model);

	public void update(M model);

	public void merge(M model);

	public void delete(PK id);

	public void deleteObject(M model);

	public M get(PK id);

	/**
	 * 统计所有
	 * 
	 * @return
	 */
	public int countAll();

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<M> listAll();

	public List<M> listAll(int pn, int pageSize);

	/**
	 * 上一页
	 * 
	 * @param pk
	 * @param pn
	 * @param pageSize
	 * @return
	 */
	public List<M> pre(PK pk, int pn, int pageSize);

	/**
	 * 下一页
	 * 
	 * @param pk
	 * @param pn
	 * @param pageSize
	 * @return
	 */
	public List<M> next(PK pk, int pn, int pageSize);

	/**
	 * 是否有主键为id的对象
	 * 
	 * @param id
	 * @return
	 */
	boolean exists(PK id);

	public void flush();

	public void clear();

}
