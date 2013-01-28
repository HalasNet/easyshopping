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
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller("searchTestAction")
@ParentPackage("struts-default")
public class ProductSearchAction extends ActionSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductSearchAction.class);

	@Autowired
	private ProductSearchService productSearchService;
	
	private List<ProductIndex> productIndexs;
	
	private String kw;

	@Action(value = "/search", results = { @Result(name = SUCCESS, location = "/product_search.jsp") })
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
				this.productIndexs = productSearchService.QueryByIndex(this.kw, "", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
