package com.ibm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.ibm.domain.User;
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
@Results({@Result(name = "left",location = "/left.jsp")})
public class MainAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9094633340535203616L;
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
	
	private HttpServletRequest request;
	
	@Action(results=@Result(name = "success", location = "/main.jsp"))
	public String index(){
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		Object user = au.getPrincipal();
		if(user instanceof User){
			HttpSession session = request.getSession();
			session.setAttribute(CURRENT_USER_ID, ((User)user).getId());
		}
		return SUCCESS;
	}

	public String left(){
		return "left";
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


}
