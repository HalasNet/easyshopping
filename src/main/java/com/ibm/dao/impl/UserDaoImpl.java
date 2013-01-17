package com.ibm.dao.impl;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ibm.dao.BaseDao;
import com.ibm.dao.UserDao;
import com.ibm.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	@Override
	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public void deletaUserById(Long userId) {
		this.getHibernateTemplate().delete("User", userId);

	}

	@Override
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);

	}

	@Override
	public User getUserById(Long userId) {
		return (User) this.getHibernateTemplate().get("User", userId);
	}

	@Override
	public User getUserByNameAndPwd(String name, String password) {
		Query query = this.getSession().createQuery(
				"from User where userName=? and password=?");
		query.setString(0, name);
		query.setString(1, password);

		User user = (User) query.uniqueResult();
		return user;
	}

}
