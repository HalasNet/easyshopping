package com.ibm.backend.action.product;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.common.dto.Pagination;
import com.ibm.common.util.Constants;
import com.ibm.model.product.Product;
import com.ibm.model.product.ProductCategory;
import com.ibm.service.product.ProductCategoryService;
import com.ibm.service.product.ProductService;
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
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 产品类别Id
	 */
	private Long categoryId;
	
	/**
	 * 类别名称-查询使用
	 */
	public String categoryName = "";
	
	/**
	 * 结果码
	 */
	public String errorMsg = "";

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
		ProductCategory category = productCategoryService.queryCategoryByName(productCategory.getCategoryName());
		if (null != category)
		{
			errorMsg ="该产品类别已经存在,请重新添加!";
		}
		else 
		{			
			productCategoryService.addProductCategory(productCategory);
		}
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
	
	/**
	 * 删除产品类别
	 * @return 删除是否成功
	 */
	public String deleteProductCategory()
	{
		Pagination pagination = new Pagination(0,Constants.DEFAULT_PAGE_SIZE);
		List<Product> listProducts = productService.search(categoryId, "", pagination);
		if (null != listProducts && !listProducts.isEmpty()) 
		{
			errorMsg ="删除失败,该产品类别下有产品,请先删除产品!";
			return "operateProductCategory";
		}
		//删除产品类别
		productCategoryService.deleteProductCategory(categoryId);
		
		return "operateProductCategory";
	}
	
	
	/**
	 * 批量删除产品类别
	 * @return 删除是否成功
	 */
	public String deleteBatchProductCategory()
	{
		String categoryIds = ServletActionContext.getRequest().getParameter("categoryIds");
		
		List<Product> listProducts = productService.queryProductsByCategoryIds(categoryIds);
		if (null != listProducts && !listProducts.isEmpty()) 
		{
			errorMsg ="删除失败,产品类别下有产品,请先删除产品!";
			return "operateProductCategory";
		}
		//批量删除产品类别
		productCategoryService.deleteBatchProductCategory(categoryIds);
		
		return "operateProductCategory";
	}
	
	/**
	 * 根据类别名称查询
	 * @return String 查询是否成功
	 */
	public String queryCategoryByName()
	{
		categoryName = productCategory.getCategoryName();
		listCategory = productCategoryService.queryCategorysByName(productCategory.getCategoryName().trim());
		return "index";
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

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
