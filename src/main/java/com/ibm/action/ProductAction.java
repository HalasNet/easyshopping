package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.domain.Product;
import com.ibm.service.product.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@ParentPackage("publicPackage")
@SuppressWarnings("serial")
@Namespace("/admin")
@Results({ @Result(name = "index", location = "/view/auth/product-index.ftl"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "product" },location = "/view/auth/product-index.ftl") })
public class ProductAction extends ActionSupport implements ModelDriven<Object> {

	@Autowired
	private ProductService productService;

	// 封装 id 请求参数的属性
	private long id;
	private Product model = new Product();
	private List<Product> list;
	private String method;

	// 获取 id 请求参数的方法
	public void setId(long id) {
		this.id = id;
		// 取得方法时顺带初始化 model 对象
		if (id > 0) {
			this.model = productService.get(id);
		}
	}

	public long getId() {
		return this.id;
	}

	// 处理不带 id 参数的 GET 请求
	public HttpHeaders index() {
		list = productService.getAll();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// 处理不带 id 参数的 GET 请求
	public String editNew() {
		model = new Product();
		return "editNew";
	}

	// 处理不带 id 参数的 POST 请求
	public HttpHeaders create() {
		productService.saveOrUpdate(model);
		return new DefaultHttpHeaders("success").setLocationId(model.getId());
	}

	// 处理带 id 参数的 GET 请求
	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// 处理带 id 参数、且指定操作 edit 资源的 GET 请求
	public String edit() {
		return "edit";
	}

	// 处理带 id 参数的 PUT 请求
	public String update() {
		productService.saveOrUpdate(model);
		return "success";
	}

	// 处理带 id 参数，且指定操作 deleteConfirm 资源的方法
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// 处理带 id 参数的 DELETE 请求
	public String destroy() {
		productService.remove(id);
		return "success";
	}

	// 实现 ModelDriven 接口必须实现的 getModel 方法
	public Object getModel() {
		return (list != null ? list : model);
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
