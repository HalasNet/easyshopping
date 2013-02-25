package com.ibm.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ibm.domain.Authority;
import com.ibm.service.user.AuthorityService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("publicPackage")
@Namespace("/admin")
@SuppressWarnings("serial")
@Results( { @Result(name = "authview", location = "/view/auth/add_authority.jsp"),
		@Result(name = "authmodify", location = "/view/auth/mod_authority.jsp"),
		@Result(name = "success", location = "/view/auth/authority.jsp"),
		@Result(name = "error", location = "/error.jsp") })
public class AuthorityAction extends ActionSupport {

	private Long id;

	private Authority authority;

	private AuthorityService authorityService;

	private List<Authority> list;

	private String[] crud;
	
	public String index() {
		list = authorityService.getAuthorityByName("");
		if (list != null) {
			return "success";
		} else {
			return "error";
		}
	}
	
	public String view(){
		String name = authority.getName();
		if(StringUtils.isBlank(name)){
			return "success";
		}
		list = authorityService.getAuthorityByName(name);
		if (list != null) {
			return "success";
		} else {
			return "error";
		}
	}

	/**
	 * 进入新建页面
	 * 
	 * @return
	 */
	public String viewCreate() {
		return "authview";
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String viewModify() {
		authority = authorityService.getAuthority(id);
		return "authmodify";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		authorityService.save(authority);
		return index();
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String modify() {
		
		Authority entity = authorityService.getAuthority(authority.getId());
		if(entity!=null){
			if(entity.getCrud()!=null && !entity.getCrud().isEmpty()){
				Set<Authority> auths = entity.getCrud();
				entity.setCrud(null);
				for(Authority delAuth : auths)
					authorityService.delete(delAuth);
			}
		}
		entity.setCrud(null);
		if(crud!=null && crud.length>0){
			Set<Authority> subs = new HashSet<Authority>();
			entity.setCrud(subs);
			Authority sub = null;
			for(String code : crud){
				sub = new Authority();
				sub.setAuthcode(authority.getAuthcode()+code);
				sub.setName(code);
				sub.setParent(authority);
				subs.add(sub);
			}
		}
		entity.setAuthcode(authority.getAuthcode());
		entity.setComment(authority.getComment());
		entity.setName(authority.getName());
		entity.setRequestURI(authority.getRequestURI());
		authorityService.modify(entity);
//		System.out.println(crud);
		
		return index();
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String del() {
		authorityService.del(id);
		return index();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String[] getCrud() {
		return crud;
	}

	public void setCrud(String[] crud) {
		this.crud = crud;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public List<Authority> getList() {
		return list;
	}

	public void setList(List<Authority> list) {
		this.list = list;
	}

}
