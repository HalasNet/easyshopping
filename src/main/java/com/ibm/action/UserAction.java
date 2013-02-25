package com.ibm.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.ibm.dao.impl.UserDaoImpl;
import com.ibm.domain.Role;
import com.ibm.domain.User;
import com.ibm.domain.UserAccount;
import com.ibm.service.user.RoleService;
import com.ibm.service.user.UserService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("publicPackage")  
@SuppressWarnings("serial")
@Controller("userAction")
@Results({ @Result(name = "list", location = "/view/auth/user_list.ftl"),
		@Result(name = "asignRole", location = "/view/auth/user_asignRole.ftl"),
		@Result(name = "user_add", location = "/view/auth/user_add.jsp"),
		@Result(name = "user_update", location = "/view/auth/user_update.jsp"),
		@Result(name = "redirectUser", type = "redirectAction", params = {
				"actionName", "user" }),
		@Result(name = "delete", location = "user-delete.ftl") })
public class UserAction extends ActionSupport {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImpl.class);

	private String userName;

	private String password;

	private List<User> users;

	private Long id;

	private User user;

	private List<Role> roles;
	
	private Long[] ids;
	
	private UserService userService;
	@Resource
	private RoleService roleService;

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

	@PreAuthorize("hasRole('user_mgr_add')")
	public String save(){
		if(user == null || StringUtils.isBlank(user.getUserName()))
			return ERROR;
		user.setPassword("111111");
		userService.saveUser(user);
		logger.info("save user complete");
		return "redirectUser";
	}
	public String index() {
		users = userService.listAll();
		return "list";
	}
	public String view(){
		String name = user.getUsername();
		if(StringUtils.isBlank(name)){
			return "success";
		}
		users = userService.listAll();
		if (users != null) {
			return "success";
		} else {
			return "error";
		}
	}
	@PreAuthorize("hasRole('user_mgr_add')")
	public String register(){
		return "user_add";
	}
	/**
	 * 更新用户
	 * @return
	 */
	public String viewModify(){
		user = userService.getUser(id);
		return "user_update";
	}
	/**
	 * 分配角色
	 * @return
	 */
	public String asignRole(){
		roles = roleService.getAll();
		users = userService.queryUserByIds(ids);
		return "asignRole";
	}
	
	public String saveRole(){
		System.out.println(users);
		if(users!=null && !users.isEmpty()){
			Set<Role> realRoles = new HashSet<Role>();
			if(roles!=null && !roles.isEmpty())
				for(Role r : roles){
					if(r!=null)
					realRoles.add(r);
				}
			for(User u : users){
				u = userService.getUser(u.getId());
				u.setRoles(realRoles);
				userService.update(u);
			}
		}
		return "redirectUser";
	}
	
	public String delete() {
		if(id!=null)
			userService.delete(id);
		return "redirectUser";
	}
	
	public String update(){
		User u = userService.getUser(id);
		UserAccount ua = u.getUserAccount();
		ua.setAddress(user.getUserAccount().getAddress());
		ua.setMobile(user.getUserAccount().getMobile());
		ua.setPhone(user.getUserAccount().getPhone());
		userService.update(u);
		return "redirectUser";
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	@Resource
	public void setUserService(UserService userService) {
		System.out.println("di userService");
		this.userService = userService;
	}

}
