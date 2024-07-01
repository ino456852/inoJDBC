package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}

	public void insertMember(Member member) {
		mDao.insertMember(member);
	}

	public List<Member> printAllMember() {
		List<Member> member = mDao.selectList();
		return member;
	}

	public Member printOneMember(String memberId) {
		Member member = mDao.selectOne(memberId);
		return member;
	}

	public int modifiMember(Member modifyInfo) {
		int result = mDao.updateMember(modifyInfo);
		return result;
	}

	public int removeMember(String memberId) {
		int result = mDao.deleteMember(memberId);
		return result;
	}

}
