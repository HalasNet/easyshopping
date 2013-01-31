package com.ibm.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.domain.AuthTree;
import com.ibm.domain.Authority;
import com.ibm.domain.Role;
import com.ibm.service.user.AuthorityService;
import com.ibm.service.user.RoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("publicPackage")
@Namespace("/admin")
@SuppressWarnings("serial")
@Results( {
		@Result(name = "index", location = "/view/role/role_list.jsp"),
		@Result(name = "roleview", location = "/view/role/role_add.jsp"),
		@Result(name = "edit", location = "/view/role/role_edit.jsp"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "role" }),
		@Result(name = "show", location = "role-show.ftl") })
public class RoleAction extends ActionSupport implements ModelDriven<Object> {

	private Long id;

	private Long roleId;

	private Role model = new Role();

	private Role role;

	private AuthorityService authorityService;

	private List<Authority> authorityList;

	private List<AuthTree> authTreeList;

	private String authorityStr;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	private List<Role> list;

	@Autowired
	private RoleService RoleService;

	// 获取 id 请求参数的方法
	public void setId(Long id) {
		this.id = id;
		// 取得方法时顺带初始化 model 对象
		if (id > 0) {
			this.model = RoleService.get(id);
		}
	}

	public Long getId() {
		return this.id;
	}

	// 处理不带 id 参数的 GET 请求
	// 进入首页
	public HttpHeaders index() {

		list = RoleService.getAll();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	/**
	 * 进入新建页面
	 * 
	 * @return
	 */
	public String viewCreate() {
		authorityList = authorityService.queryAll();
		return "roleview";
	}

	// 处理不带 id 参数的 GET 请求
	public String editNew() {
		model = new Role();
		return "editNew";
	}

	// 处理不带 id 参数的 POST 请求
	public HttpHeaders create() {
		Set<Authority> authorities = new HashSet<Authority>();
		String[] idarr = authorityStr.split(":");
		for (String id : idarr) {
			Authority authority = new Authority();
			authority.setId(Long.parseLong(id));
			authorities.add(authority);
		}
		model.setAuthorities(authorities);
		RoleService.saveOrUpdate(model);
		addActionMessage("添加成功");
		return new DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	// 处理带 id 参数的 GET 请求 /role/1
	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// 处理带 id 参数、且指定操作 edit 资源的 GET 请求
	// 进入编辑页面 (role-edit.jsp)
	public String edit() {
		authorityList = authorityService.queryAll();
		authTreeList = new ArrayList<AuthTree>();
		role = RoleService.get(roleId);
		for (Authority auth : authorityList) {
			Long id = auth.getId();
			AuthTree authTree = new AuthTree();
			authTree.setAuthority(auth);
			for (Authority roleAuth : role.getAuthorities()) {
				if (id == roleAuth.getId()) {
					authTree.setIsChecked("true");
				}
			}
			if (StringUtils.isBlank(authTree.getIsChecked())) {
				authTree.setIsChecked("");
			}
			authTreeList.add(authTree);
		}

		return "edit";
	}

	// 处理带 id 参数的 PUT 请求
	public String update() {
		Set<Authority> authorities = new HashSet<Authority>();
		String[] idarr = authorityStr.split(":");
		for (String id : idarr) {
			if (StringUtils.isNotBlank(id)) {
				Authority authority = new Authority();
				authority.setId(Long.parseLong(id));
				authorities.add(authority);
			}
		}
		model.setAuthorities(authorities);
		RoleService.update(model);
		return "success";
	}

	// 处理带 id 参数，且指定操作 deleteConfirm 资源的方法
	// 进入删除页面 (Role-deleteConfirm.jsp)
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// 处理带 id 参数的 DELETE 请求
	public String destroy() {
		RoleService.remove(roleId);
		addActionMessage("成功删除 ID 为" + id + "的role！");
		return "success";
	}

	// 实现 ModelDriven 接口必须实现的 getModel 方法
	public Object getModel() {
		return (list != null ? list : model);
	}

	public String view() {
		String name = role.getRoleName();
		if (StringUtils.isBlank(name)) {
			return "index";
		}
		list = RoleService.getRoleByName(name);
		return "index";
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public String getAuthorityStr() {
		return authorityStr;
	}

	public void setAuthorityStr(String authorityStr) {
		this.authorityStr = authorityStr;
	}

	public List<AuthTree> getAuthTreeList() {
		return authTreeList;
	}

	public void setAuthTreeList(List<AuthTree> authTreeList) {
		this.authTreeList = authTreeList;
	}

}
