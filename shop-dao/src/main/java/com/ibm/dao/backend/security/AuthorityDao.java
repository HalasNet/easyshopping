package com.ibm.dao.backend.security;

import java.util.List;


import com.ibm.dao.IBaseDao;
import com.ibm.model.backend.security.Authority;

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