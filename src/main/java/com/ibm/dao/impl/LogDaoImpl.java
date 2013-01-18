package com.ibm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.LogDao;
import com.ibm.domain.Log;

@Repository("logDao")
public class LogDaoImpl extends BaseHibernateDao<Log, Long> implements LogDao {

}
