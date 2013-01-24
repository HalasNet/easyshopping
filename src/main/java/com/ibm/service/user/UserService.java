package com.ibm.service.user;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.LogDao;
import com.ibm.dao.UserDao;
import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.Log;
import com.ibm.domain.User;

@Service
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

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDao.getUserByName(username);
	}

}
