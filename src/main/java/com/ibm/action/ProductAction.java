package com.ibm.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.domain.Product;
import com.ibm.domain.ProductCategory;
import com.ibm.service.product.ProductService;
import com.ibm.service.user.ProductCategoryService;
import com.ibm.util.Constants;
import com.ibm.util.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@ParentPackage("publicPackage")
@SuppressWarnings("serial")
@Namespace("/admin")
@Results({
		@Result(name = "index", location = "/view/product/product-index.ftl"),
		@Result(name = "success", location = "/view/product/product-index.ftl") })
public class ProductAction extends ActionSupport implements ModelDriven<Object> {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	// 封装 id 请求参数的属性
	private long id;
	private Product model = new Product();
	private List<Product> list;
	private List<ProductCategory> categoryList;
	private long categoryId;
	private String productName;
	private Pagination pagination;
	private int total;
	private int curPage;
	private int prePage;
	private int nextPage;
	private int totalPage;
	private int startNum;
	private int endNum;

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
		pagination = new Pagination(curPage, Constants.DEFAULT_PAGE_SIZE);
		list = productService.search(categoryId, model.getProductName(),
				pagination);
		pagination = pagination.getPage();
		categoryList = productCategoryService.queryCategorys();
		total = pagination.getTotal();
		curPage = pagination.getCurPage();
		prePage = pagination.getPrePage();
		nextPage = pagination.getNextPage();
		totalPage = pagination.getTotalPage();
		startNum = pagination.getStartNum();
		endNum = pagination.getEndNum();
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

	public String search() {
		pagination = new Pagination(curPage, Constants.DEFAULT_PAGE_SIZE);
		list = productService.search(categoryId, model.getProductName(),
				pagination);
		pagination = pagination.getPage();
		categoryList = productCategoryService.queryCategorys();
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

	public List<ProductCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<ProductCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

}
