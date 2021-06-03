package com.example.demo.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Member;
import com.example.demo.repository.rowMapper.AdminRowMapper;
import com.example.demo.repository.rowMapper.MemberRowMapper;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public ArrayList<String> getAllMailIds() {
		String sql="select member_mail_id from members;";
		ArrayList<String> mailIdList=(ArrayList<String>) jdbcTemplate.queryForList(sql, String.class);
		return mailIdList;
	}

	@Override
	public void addMember(Member member) {
		String sql="insert into members (member_mail_id, member_name,admin_mail_id,mpassword,phone_number) values (?,?,?,?,?);";
		jdbcTemplate.update(sql, member.getMemberMailId(),member.getMemberName(),member.getAdminMailId(),member.getMpassword(),member.getPhoneNumber());
	}

	public void removeByMailId(String memberMailId) {
		String sql="delete from members where member_mail_id=? ;";
		jdbcTemplate.update(sql, memberMailId);
	}

	@Override
	public Member getMemberById(String mailId) {
		String sql="select * from members where member_mail_id=?;";
		MemberRowMapper memberRowMapper=new MemberRowMapper();
		Member member= jdbcTemplate.queryForObject(sql, memberRowMapper, new Object[] { mailId });
		return member;
	}

}
