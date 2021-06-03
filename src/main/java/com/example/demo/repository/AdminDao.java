package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Admin;

public interface AdminDao{

	public List<String> getAllMailIds();

	public void addAdmin(Admin admin);

	public Admin getAdminById(String mailId);

		
}

