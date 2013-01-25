package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.User;
import com.ibm.service.user.UserService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("publicPackage")  
@SuppressWarnings("serial")
@Controller("userAction")
@Results({ @Result(name = "list", location = "/list.jsp"),
		@Result(name = "delete", location = "user-delete.ftl") })
public class UserAction extends ActionSupport {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	private String userName;

	private String password;

	private List<User> users;

	private Long id;

	private User user;

	@Autowired
	private UserService userService;

	@Action(results = {
			@Result(name = "success", location = "/main.jsp"),
			@Result(name = "error", location = "/error.jsp") })
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

	public String index() {
		users = userService.listAll();
		return "list";
	}

	public String delete() {
		return "delete";
	}
	
	public String update(){
		return "delete";
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
