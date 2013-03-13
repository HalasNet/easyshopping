package com.ibm.model.backend.security;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ibm.model.IdEntity;

@Entity
@Table(name = "t_user_account")
public class UserAccount extends IdEntity implements Serializable {

	private static final long serialVersionUID = -5636681049900205858L;

	private String phone;

	private String mobile;

	private String address;

	private User user;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(mappedBy = "userAccount", cascade = { CascadeType.MERGE,
			CascadeType.REFRESH }, optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
