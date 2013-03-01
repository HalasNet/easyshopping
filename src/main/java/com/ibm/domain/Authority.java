package com.ibm.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 *  Authority entity, as the rights or resource
 *
 * @author qzq
 * @since 2012-10-20
 */
@Entity
@Table(name="t_authority")
public class Authority extends IdEntity implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4758177723897081575L;
	
	public static final String LOGIN_USER_AUTHCODE = "ROLE_LOGIN_USER";
	public static final String LOGIN_INTERCEPT_PATH = "/**";
	
	private String name;
	
	private String comment;
	
	private String authcode;
	/**
	 * the current authority mapped the request URI
	 */
	private String requestURI;

	private Authority parent;
	
	private Set<Authority> crud;
	
//	private Set<Role> roles;
	
	@Transient
	@Override
	public String getAuthority() {
		return authcode;
	}
	
	@Column(nullable=false,length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length=50)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(nullable=false,length=30,unique=true)
	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	@Column(length=50)
	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	
	@ManyToOne
	public Authority getParent() {
		return parent;
	}

	public void setParent(Authority parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	public Set<Authority> getCrud() {
		return crud;
	}

	public void setCrud(Set<Authority> crud) {
		this.crud = crud;
	}
/*
	@ManyToMany(mappedBy="authorities",cascade={})
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
*/
	@Override
	public String toString() {
		return "Authority -- id:"+id;
	}
}
