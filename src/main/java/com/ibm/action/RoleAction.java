package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.domain.Role;
import com.ibm.service.user.RoleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("publicPackage")
@SuppressWarnings("serial")
@Results(@Result(name = "success", type = "redirectAction", params = {
		"actionName", "role" }))
public class RoleAction extends ActionSupport implements ModelDriven<Object> {
	
	private Long id;
	
	private Role model = new Role();
	
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

	// 处理不带 id 参数的 GET 请求
	public String editNew() {
		model = new Role();
		return "editNew";
	}

	// 处理不带 id 参数的 POST 请求
	public HttpHeaders create() {
		RoleService.saveOrUpdate(model);
		addActionMessage("添加成功");
		return new DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	// 处理带 id 参数的 GET 请求
	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// 处理带 id 参数、且指定操作 edit 资源的 GET 请求
	// 进入编辑页面 (role-edit.jsp)
	public String edit() {
		return "edit";
	}

	// 处理带 id 参数的 PUT 请求
	public String update() {
		RoleService.saveOrUpdate(model);
		addActionMessage("编辑成功！");
		return "success";
	}

	// 处理带 id 参数，且指定操作 deleteConfirm 资源的方法
	// 进入删除页面 (Role-deleteConfirm.jsp)
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// 处理带 id 参数的 DELETE 请求
	public String destroy() {
		RoleService.remove(id);
		addActionMessage("成功删除 ID 为" + id + "的role！");
		return "success";
	}

	// 实现 ModelDriven 接口必须实现的 getModel 方法
	public Object getModel() {
		return (list != null ? list : model);
	}

}
