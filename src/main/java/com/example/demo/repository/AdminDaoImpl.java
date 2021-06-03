package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;
import com.example.demo.repository.rowMapper.AdminRowMapper;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	public JdbcTemplate template;
	
	final String INSERT_QUERY = "insert into admins (admin_mail_id,admin_name,apassword) values (?,?,?);";
	final String GET_BY_ID = "select * from admins where admin_mail_id=?;";

	@Override
	public List<String> getAllMailIds() {
		List<String> mailIdList=new ArrayList<>();
		
		String getAllMailIds="select admin_mail_id from admins;";
		mailIdList.addAll(template.queryForList(getAllMailIds, String.class));
		
		return mailIdList;
	}

	@Override
	public void addAdmin(Admin admin) {
		template.update(INSERT_QUERY,admin.getAdminMailId(),admin.getAdminName(),admin.getApassword());
	}

	@Override
	public Admin getAdminById(String mailId) {
		AdminRowMapper adminRowMapper=new AdminRowMapper();
		Admin admin= template.queryForObject("select * from admins where  admin_mail_id=?", 
				adminRowMapper, new Object[] { mailId });
		return admin;
	}
	
}
