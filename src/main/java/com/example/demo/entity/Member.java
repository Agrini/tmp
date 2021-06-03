package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="members")
public class Member {
//
//	create table members(
//			member_mail_id varchar(30) auto_increment primary key,
//            member_name varchar(30),
//            doj date default (current_date),
//            admin_mail_id varchar(30),
//            mpassword varchar(20),
//            phone_number bigint,
//            penalty decimal(6,2) default 0,
//            foreign key (admin_mail_id) references admins(admin_mail_id)
//            );
	@Id
	@Column(name="member_mail_id",length=30)
	private String memberMailId;
	
	@Column(name="member_name",length=30)
	private String memberName;
	
	@Column(name="doj")
	private Date doj;
	
	@Column(name="admin_mail_id",length=30)
	private String adminMailId;
	
	@Column(name="mpassword",length=20)
	private String mpassword;
	
	@Column(name="phone_number")
	private Long phoneNumber;
	
	@Column(name="penalty")
	private float penalty;

	public String getMemberMailId() {
		return memberMailId;
	}

	public void setMemberMailId(String memberMailId) {
		this.memberMailId = memberMailId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getAdminMailId() {
		return adminMailId;
	}

	public void setAdminMailId(String adminMailId) {
		this.adminMailId = adminMailId;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public float getPenalty() {
		return penalty;
	}

	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}
	
	
	
}
