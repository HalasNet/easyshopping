package com.ibm.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "t_user")
public class User extends IdEntity implements Serializable,UserDetails {

	private static final long serialVersionUID = -7156991274448325288L;

	private String userName;

	private String password;

	private Date birthday;

	private UserAccount userAccount;

	private Set<Role> roles;

	@Column(name = "userName")
	@NotBlank
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password")
	@NotBlank
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "birthday")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@OneToOne(optional = false, cascade = { CascadeType.ALL },fetch=FetchType.LAZY)
	@JoinColumn(name = "accountId")
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@ManyToMany
	@JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		// the login user have default login authority
		Authority loginAuth = new Authority();
		loginAuth.setAuthcode(Authority.LOGIN_USER_AUTHCODE);
		loginAuth.setRequestURI(Authority.LOGIN_INTERCEPT_PATH);
		auths.add(loginAuth);

		Set<Role> roles = this.roles;
		if(roles!=null && !roles.isEmpty()){
			for(Role role : roles){
				auths.addAll(role.getAuthorities());
			}
		}
		return auths;
	}
	@Transient
	@Override
	public String getUsername() {
		return userName;
	}
	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Transient
	@Override
	public boolean isEnabled() {
		return true;
	}

}
