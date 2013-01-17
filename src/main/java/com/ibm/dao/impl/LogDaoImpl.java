package com.ibm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseDao;
import com.ibm.dao.LogDao;
import com.ibm.domain.Log;

@Repository("logDao")
public class LogDaoImpl extends BaseDao implements LogDao {

	@Override
	public void addLog(Log log) {
		System.out.println(9/0);
		this.getHibernateTemplate().save(log);
	}

}
