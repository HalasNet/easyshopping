package com.ibm.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_role")
public class Role extends IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2178247159991258572L;

	private String roleName;

	private String remark;

	private Set<User> user;

	private Set<Authority> authorities;
	
	public String getRolename() {
		return roleName;
	}

	public void setRolename(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToMany(mappedBy = "roles",fetch=FetchType.LAZY)
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@ManyToMany
	@JoinTable(name = "t_role_authority", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "authorityId"))
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

}
