package com.example.demo.repository.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.Admin;

public class AdminRowMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

		Admin admin=new Admin();
		
		admin.setAdminMailId(rs.getString("admin_mail_id"));
		admin.setAdminName(rs.getString("admin_name"));
		admin.setDoj(rs.getDate("doj"));
		admin.setApassword(rs.getString("apassword"));
		return admin;
	}

}
