package com.ibm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.RoleDao;
import com.ibm.domain.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseHibernateDao<Role, Long> implements
		RoleDao {

}
