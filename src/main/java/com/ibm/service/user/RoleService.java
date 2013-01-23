package com.ibm.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.RoleDao;
import com.ibm.domain.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	@Transactional(readOnly = true)
	public Role get(Long id) {
		return roleDao.get(id);
	}

	@Transactional(readOnly = true)
	public List<Role> getAll() {
		return roleDao.listAll();
	}

	@Transactional
	public void saveOrUpdate(Role role) {
		roleDao.saveOrUpdate(role);
	}

	@Transactional
	public void remove(Long id) {
		roleDao.delete(id);
	}

}
