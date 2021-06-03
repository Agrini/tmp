package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.exception.MailIdAndPasswordMismatch;
import com.example.demo.exception.NoSuchMailIdFoundException;
import com.example.demo.repository.MemberDao;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;
	
	

	public Boolean checkAndAdd(Member member) {	
		ArrayList<String> mailIdList=memberDao.getAllMailIds();
		
		System.out.println(mailIdList);
		if(mailIdList.contains(member.getMemberMailId())){
			return false;
		}else {
			memberDao.addMember(member);
			return true;
		}
	}

	public Boolean checkAndRemoveByMailId(String memberMailId) throws NoSuchMailIdFoundException {
		ArrayList<String> mailIdList=memberDao.getAllMailIds();
		
		if(!mailIdList.contains(memberMailId)) {
			throw new NoSuchMailIdFoundException();
		}else {
			memberDao.removeByMailId(memberMailId);
			return true;
		}
	}

	public Member checkAndGetMember(String mailId, String password) throws NoSuchMailIdFoundException, MailIdAndPasswordMismatch {
		ArrayList<String> mailIdList=memberDao.getAllMailIds();
		
		if(!mailIdList.contains(mailId)) {
			throw new NoSuchMailIdFoundException();
		}else {
			Member member=memberDao.getMemberById(mailId);
			if(!member.getMpassword().equals(password)) {
				throw new MailIdAndPasswordMismatch();
			}
			else {
				return member;
			}
		}
	}
	
	
}
