package com.ibm.model.backend.system;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ibm.model.IdEntity;

@Entity
@Table(name = "t_log")
public class Log extends IdEntity implements Serializable {

	private static final long serialVersionUID = 2831790069329668965L;

	private String remark;

	private Date logTime;

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "logTime")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}
