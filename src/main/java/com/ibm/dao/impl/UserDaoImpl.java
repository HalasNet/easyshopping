package com.ibm.dao.impl;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseHibernateDao;
import com.ibm.dao.UserDao;
import com.ibm.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseHibernateDao<User, Long> implements
		UserDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Override
	public User getUserByNameAndPwd(String name, String password) {
		Query query = this.getSession().createQuery(
				"from User where userName=? and password=?");
		query.setString(0, name);
		query.setString(1, password);

		User user = (User) query.uniqueResult();
		return user;
	}

	@Override
	public User getUserByName(String username) {
		logger.info("execute UserDaoImpl method getUserByName()");
		String hql = "from User where userName = ?";
		return unique(hql, username);
	}

}
