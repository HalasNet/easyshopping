package com.ibm.service.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ibm.dao.LogDao;
import com.ibm.dao.UserDao;
import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.Log;
import com.ibm.domain.User;

@Service
@Transactional
public class UserService implements UserDetailsService{

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private LogDao logDao;

	@Transactional
	public void saveUser(User user) {
		userDao.save(user);
		Log log = new Log();
		log.setRemark("add user:" + user.getUserName());
		log.setLogTime(new Date());
		logDao.save(log);
	}

	public void delete(Long id){
		Assert.notNull(id);
		logger.info("delete user by id :"+id);
		userDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public User loginUser(String name, String password) {
		return userDao.getUserByNameAndPwd(name, password);
	}

	@Transactional(readOnly = true)
	public List<User> listAll() {
		return userDao.listAll();
	}


	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.get(id);
	}

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDao.getUserByName(username);
	}

	public List<User> queryUserByIds(Long[] ids){
		return userDao.queryUserByIds(ids);
	}
	
	public void update(User user){
		userDao.update(user);
	}
}
