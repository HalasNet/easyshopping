package com.ibm.dao;

import java.util.Collection;
import java.util.List;

import com.ibm.domain.User;

public interface UserDao extends IBaseDao<User, Long>{
	
	public User getUserByNameAndPwd(String name,String password);

	public User getUserByName(String username);
	
	public List<User> queryUserByIds(Long[] ids);
}
