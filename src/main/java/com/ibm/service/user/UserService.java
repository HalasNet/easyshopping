package com.ibm.service.user;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.dao.LogDao;
import com.ibm.dao.UserDao;
import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.Log;
import com.ibm.domain.User;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private LogDao logDao;

	@Transactional
	public void saveUser(User user) {
		userDao.addUser(user);
		Log log = new Log();
		log.setRemark("add user:"+user.getUserName());
		log.setLogTime(new Date());
		logDao.addLog(log);
	}

	@Transactional(readOnly = true)
	public User loginUser(String name, String password) {
		return userDao.getUserByNameAndPwd(name, password);
	}

}
