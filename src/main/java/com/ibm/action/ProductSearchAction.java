package com.ibm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.domain.ProductIndex;
import com.ibm.service.search.ProductSearchService;
import com.opensymphony.xwork2.ActionContext;
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

	public String autoComplete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		String keyword = request.getParameter("q");
		List<Object> list = new ArrayList<Object>(); 
		StringBuffer value= new StringBuffer();
		try {
			keyword = URLDecoder.decode(keyword,"UTF-8");
			if (keyword != null && !keyword.equals("")){
				this.productIndexs = productSearchService.QueryByIndex(keyword, "", "");
			}
			for(int i = 0; i < productIndexs.size(); i++) {
				ProductIndex p = productIndexs.get(i);
				value.append(p.getProductName() + "\n");
				list.add(p.getProductName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = null;
        try {
    		out = response.getWriter();
    		response.setContentType("text/json; charset=UTF-8");
    		out.print(value);
            out.flush();
            out.close();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
		return null;
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
