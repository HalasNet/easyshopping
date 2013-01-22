package com.ibm.domain;

import java.io.Serializable;
import java.util.Date;
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

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_user")
public class User extends IdEntity implements Serializable {

	private static final long serialVersionUID = -7156991274448325288L;

	private String userName;

	private String password;

	private Date birthday;

	private UserAccount userAccount;

	private Set<Order> orders;

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

	@OneToOne(optional = false, cascade = { CascadeType.ALL })
	@JoinColumn(name = "accountId")
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@ManyToMany
	@JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
