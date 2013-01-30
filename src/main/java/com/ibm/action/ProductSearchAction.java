package com.ibm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.domain.ProductIndex;
import com.ibm.service.search.ProductSearchService;
import com.ibm.util.Constants;
import com.ibm.util.page.Pagination;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("searchTestAction")
@ParentPackage("struts-default")
@Action(value = "/search", results = { @Result(name = "success", location = "/view/search/product_search.jsp") })
public class ProductSearchAction extends ActionSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductSearchAction.class);

	@Autowired
	private ProductSearchService productSearchService;
	
	private List<ProductIndex> productIndexs;
	
	
	private Pagination pagination;
	
	private int total;
	private int curPage;

	
	private String kw;

	
	public String index() {
		return SUCCESS;
	}
	
	public String crud() {
		productSearchService.createIndexByHibernateSearch();
		return SUCCESS;
	}
	
	public String search() {
		try {
			if (this.kw != null && !this.kw.equals("")){
				pagination = new Pagination(curPage, Constants.DEFAULT_PAGE_SIZE);
				this.productIndexs = productSearchService.search(kw, pagination);
				this.pagination = pagination.getPage();
				this.total = pagination.getTotal();
				this.curPage = pagination.getCurPage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String test() {
		productSearchService.indexTest();
		return SUCCESS;
	}

	
	public List<ProductIndex> getProductIndexs() {
		return productIndexs;
	}

	public void setProductIndexs(List<ProductIndex> productIndexs) {
		this.productIndexs = productIndexs;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}




}
