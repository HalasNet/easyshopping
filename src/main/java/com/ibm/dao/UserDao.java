package com.ibm.dao;

import com.ibm.domain.User;

public interface UserDao {
	
	public void addUser(User user);
	
	public void deletaUserById(Long userId);
	
	public void updateUser(User user);
	
	public User getUserById(Long userId);
	
	public User getUserByNameAndPwd(String name,String password);

}
