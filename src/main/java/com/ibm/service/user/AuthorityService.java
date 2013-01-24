package com.ibm.service.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.AuthorityDao;
import com.ibm.dao.LogDao;
import com.ibm.dao.UserDao;
import com.ibm.domain.Authority;
import com.ibm.domain.Log;
import com.ibm.domain.User;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-23 下午2:17:38
 */
@Service
@Transactional
public class AuthorityService {

	@Resource
	private AuthorityDao authorityDao;

	@Autowired
	private LogDao logDao;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Authority> queryAll() {
		return authorityDao.listAll();
	}
	
	@Transactional
	public List<Authority> getAuthorityByName(String name) {
		return authorityDao.getAuthorityByName(name);
	}
	

	@Transactional
	public void save(Authority authority) {
		authorityDao.save(authority);
		Log log = new Log();
		log.setRemark("add authority:" + authority.getName());
		log.setLogTime(new Date());
		logDao.save(log);
	}
	
	@Transactional
	public void modify(Authority authority) {
		authorityDao.update(authority);
		Log log = new Log();
		log.setRemark("modify authority:" + authority.getName());
		log.setLogTime(new Date());
		logDao.save(log);
	}

	@Transactional
	public Authority getAuthority(Long id) {
		return authorityDao.get(id);

	}
	
	@Transactional
	public void del(Long id) {
		authorityDao.delete(id);

	}
}
