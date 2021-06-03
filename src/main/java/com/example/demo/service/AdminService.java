package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.MailIdAndPasswordMismatch;
import com.example.demo.exception.NoSuchMailIdFoundException;
import com.example.demo.repository.AdminDao;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public boolean checkAndAddAdmin(Admin admin) throws EmailAlreadyExistsException {
		boolean exists=checkAdmin(admin);
		
		if(exists){
			throw new EmailAlreadyExistsException();
		}
		else{
			adminDao.addAdmin(admin);
			return true;
		}
	}

	private boolean checkAdmin(Admin admin) {
		List<String> mailIdList=adminDao.getAllMailIds();
		
		Boolean contains=mailIdList.contains(admin.getAdminMailId());
		return contains;
	}

	public Admin checkAndGetAdmin(String mailId, String password) throws NoSuchMailIdFoundException, MailIdAndPasswordMismatch{
		List<String> mailIdList=adminDao.getAllMailIds();
		
		if(!mailIdList.contains(mailId)){
			throw new NoSuchMailIdFoundException();
		}
		else {
			Admin admin=adminDao.getAdminById(mailId);
			if(!admin.getApassword().equals(password)) {
				throw new MailIdAndPasswordMismatch();
			}
			else {
				return admin;
			}
		}
		
	}
	
	
	
}
