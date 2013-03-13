package com.ibm.model.product;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ibm.model.IdEntity;

@Entity
@Table(name = "t_member_account")
public class MemberAccount extends IdEntity implements Serializable {

	private static final long serialVersionUID = -5636681049900205858L;

	private String phone;

	private String mobile;

	private String address;

	private Member member;

	@Column(length=30)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(length=30)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(length=100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(mappedBy = "memberAccount", cascade = { CascadeType.MERGE,
			CascadeType.REFRESH }, optional = false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
