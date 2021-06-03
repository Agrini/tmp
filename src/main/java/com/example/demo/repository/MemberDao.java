package com.example.demo.repository;

import java.util.ArrayList;

import com.example.demo.entity.Member;

public interface MemberDao {

	ArrayList<String> getAllMailIds();

	void addMember(Member member);

	void removeByMailId(String memberMailId);

	Member getMemberById(String mailId);

}
