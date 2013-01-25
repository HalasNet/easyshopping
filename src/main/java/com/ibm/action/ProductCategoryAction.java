package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.domain.ProductCategory;
import com.ibm.service.user.ProductCategoryService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author dgs
 *
 */
@ParentPackage("publicPackage")  
@SuppressWarnings("serial")
@Namespace("/product")
public class ProductCategoryAction extends ActionSupport  implements ModelDriven<Object>
{
	/**
	 * 创建ProductCategory实例
	 */
	public ProductCategory productCategory =  new ProductCategory();
	
	private String categoryName;
	
	private List<ProductCategory> listCategory;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	
	/**
	 * 
	 * @return String 字符串
	 */
	@Action(results = {
			@Result(name = "success", location = "/success.jsp")})
	public String addProductCategory() 
	{
		
		return "success";
	}

	// 处理不带 id 参数的 GET 请求
	// 进入首页
	public HttpHeaders index() 
	{

		listCategory = productCategoryService.queryCategorys();
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public Object getModel() {
		return (listCategory != null ? listCategory : productCategory);
	}
	
	
	
}
