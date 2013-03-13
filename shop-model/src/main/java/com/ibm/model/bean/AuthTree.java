package com.ibm.model.bean;

import com.ibm.model.backend.security.Authority;

public class AuthTree {
	
	private Authority authority;
	
	private String isChecked;


	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}
