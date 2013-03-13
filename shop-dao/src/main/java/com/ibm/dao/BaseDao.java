package com.ibm.dao;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * 此类已废弃,请使用BaseHibernateDao
 *
 * @author david
 *
 */
@Deprecated
public abstract class BaseDao extends HibernateDaoSupport{
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@PostConstruct   
	 private void setSuperSessionFactory(){
	  super.setSessionFactory(sessionFactory);
	 }

}
