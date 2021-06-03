package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin {
//
//	admin_mail_id varchar(30) primary key,
//    admin_name varchar(30),
//    doj date default (current_date),
//    apassword varchar(20)

	@Id
	@Column(name="admin_mail_id",length=30)
	private String adminMailId; 

	@Column(name="admin_name",length=30)
	private String adminName;
	
	@Column(name="doj")
	private Date doj;
	
	@Column(name="apassword",length=20)
	private String apassword;
	
	
	public String getAdminMailId() {
		return adminMailId;
	}

	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getApassword() {
		return apassword;
	}

	public void setApassword(String password) {
		this.apassword = password;
	}
	
	
}
