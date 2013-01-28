package com.ibm.dao;

import java.util.List;

import com.ibm.domain.Role;

public interface RoleDao extends IBaseDao<Role, Long> {
	public List<Role> getRoleByName(String name);
}
