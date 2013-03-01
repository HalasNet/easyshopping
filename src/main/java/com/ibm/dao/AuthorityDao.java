package com.ibm.dao;

import java.util.List;

import com.ibm.domain.Authority;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-23 下午4:48:52
 */
public interface AuthorityDao extends IBaseDao<Authority,Long>{

	public List<Authority> getAuthorityByName(String name);
	
	public void delete(Authority entity);
}