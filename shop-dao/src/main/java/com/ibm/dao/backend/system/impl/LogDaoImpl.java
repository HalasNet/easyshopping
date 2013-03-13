package com.ibm.dao.backend.system.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.backend.system.LogDao;
import com.ibm.model.backend.system.Log;

@Repository("logDao")
public class LogDaoImpl extends BaseHibernateDao<Log, Long> implements LogDao {

}
