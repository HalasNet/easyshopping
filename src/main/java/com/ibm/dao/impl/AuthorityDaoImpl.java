package com.ibm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.AuthorityDao;
import com.ibm.dao.BaseHibernateDao;
import com.ibm.domain.Authority;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-23 下午4:50:14
 */
@Repository
public class AuthorityDaoImpl extends BaseHibernateDao<Authority, Long> implements AuthorityDao{

}
