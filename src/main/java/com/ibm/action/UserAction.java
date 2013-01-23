package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.User;
import com.ibm.service.user.UserService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("userAction")
@ParentPackage("struts-default")
public class UserAction extends ActionSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	private String userName;

	private String password;

	private List<User> users;

	@Autowired
	private UserService userService;

	public String login() {
		User user = userService.loginUser(userName, password);
		if (user != null) {
			return "success";
		} else {
			return "error";
		}
	}

	public String add() {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		userService.saveUser(user);
		return "success";
	}

	@Action(value="/user/list",results = { @Result(name = "list", location = "/list.jsp") })
	public String index() {
		users = userService.listAll();
		return "list";
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
