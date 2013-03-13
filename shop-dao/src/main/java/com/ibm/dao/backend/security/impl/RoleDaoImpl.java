package com.ibm.dao.backend.security.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.backend.security.RoleDao;
import com.ibm.model.backend.security.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseHibernateDao<Role, Long> implements
		RoleDao {
	@Override
	public List<Role> getRoleByName(String name) {
		String hql = "from Role where roleName like ?";
		return list(hql, "%" + name + "%");
	}
	
}
