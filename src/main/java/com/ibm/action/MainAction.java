package com.ibm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-24 上午10:34:33
 */
@ParentPackage("publicPackage")  
@Namespace("/admin")
@Controller
public class MainAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9094633340535203616L;

	@Action(results=@Result(name = "success", location = "/main.jsp"))
	public String index(){
		return SUCCESS;
	}
}
