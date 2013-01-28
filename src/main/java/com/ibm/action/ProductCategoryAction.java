package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
@Namespace("/admin")
@Results({ @Result(name = "index", location = "/view/auth/product-category-index.ftl") ,
			@Result(name = "addProductCategoryView", location = "/view/auth/add_category.jsp"),
			@Result(name = "operateProductCategory", location = "/view/auth/operate_category_success.jsp"),
			@Result(name = "modifyProductCategory", location = "/view/auth/modify_category.jsp")})
public class ProductCategoryAction extends ActionSupport implements
		ModelDriven<Object> {
	/**
	 * 创建ProductCategory实例
	 */
	public ProductCategory productCategory = new ProductCategory();
	
	private List<ProductCategory> listCategory;

	@Autowired
	private ProductCategoryService productCategoryService;
	
	private Long categoryId;

	/**
	 * 
	 * @return String 字符串
	 */
	public String addProductCategoryView() {

		return "addProductCategoryView";
	}
	
	/**
	 * 
	 * @return String 是否成功
	 */
	public String addProductCategory()
	{
		productCategoryService.addProductCategory(productCategory);
		return "operateProductCategory";
	}
	
	/**
	 * 
	 * @return String 字符串
	 */
	public String modifyProductCategoryView() 
	{
		//String id = ServletActionContext.getRequest().getParameter("id");
		productCategory = productCategoryService.queryProductCategoryById(categoryId);
		return "modifyProductCategory";
	}
	
	/**
	 * 修改产品类别
	 * @return String 是否成功
	 */
	public String modifyProductCategory()
	{
		//String categoryId = ServletActionContext.getRequest().getParameter("categoryId");
		//productCategory.setId(Long.parseLong(categoryId));
		
		productCategory.setId(categoryId);
		
		productCategoryService.modifyProductCategory(productCategory);
		return "operateProductCategory";
	}

	// 处理不带 id 参数的 GET 请求
	// 进入首页
	public HttpHeaders index() {

		listCategory = productCategoryService.queryCategorys();
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public Object getModel() {
		return (listCategory != null ? listCategory : productCategory);
	}

	public List<ProductCategory> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<ProductCategory> listCategory) {
		this.listCategory = listCategory;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
