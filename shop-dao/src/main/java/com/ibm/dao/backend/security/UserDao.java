package com.ibm.dao.backend.security;

import java.util.List;


import com.ibm.dao.IBaseDao;
import com.ibm.model.backend.security.User;

public interface UserDao extends IBaseDao<User, Long>{
	
	public User getUserByNameAndPwd(String name,String password);

	public User getUserByName(String username);
	
	public List<User> queryUserByIds(Long[] ids);
}
