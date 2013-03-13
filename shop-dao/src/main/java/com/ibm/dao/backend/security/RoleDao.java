package com.ibm.dao.backend.security;

import java.util.List;


import com.ibm.dao.IBaseDao;
import com.ibm.model.backend.security.Role;

public interface RoleDao extends IBaseDao<Role, Long> {
	public List<Role> getRoleByName(String name);
	
}
