package com.example.demo.repository.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

		Member member=new Member();
		
		member.setMemberMailId(rs.getString("member_mail_id"));
		member.setMemberName(rs.getString("member_name"));
		member.setDoj(rs.getDate("doj"));
		member.setAdminMailId(rs.getString("admin_mail_id"));
		member.setMpassword(rs.getString("mpassword"));
		member.setPhoneNumber(rs.getLong("phone_number"));
		member.setPenalty(rs.getFloat("penalty"));
		return member;
	}

}
