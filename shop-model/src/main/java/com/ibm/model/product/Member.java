package com.ibm.model.product;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ibm.model.IdEntity;

/**
 * 
 * @author qzq
 * 
 * @time 2013-1-24 下午5:01:31
 */
@Entity
@Table(name="t_member")
public class Member extends IdEntity implements Serializable,UserDetails{

	/**
	 * s
	 */
	private static final long serialVersionUID = -8941710984953562956L;

	private String username;
	
	private String password;
	
	private Date birthday;

	private MemberAccount memberAccount;

	private Set<Order> orders;
	
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@OneToOne(optional = false, cascade = { CascadeType.ALL },fetch=FetchType.LAZY)
	@JoinColumn(name = "accountId")
	public MemberAccount getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(MemberAccount memberAccount) {
		this.memberAccount = memberAccount;
	}
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	@Column(nullable=false,length=30)
	public String getPassword() {
		return password;
	}
	
	@Override
	@Column(nullable=false,length=50)
	public String getUsername() {
		return username;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override@Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
