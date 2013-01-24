package com.ibm.dao;

import com.ibm.domain.User;

public interface UserDao extends IBaseDao<User, Long>{
	
	public User getUserByNameAndPwd(String name,String password);

	public User getUserByName(String username);
}
