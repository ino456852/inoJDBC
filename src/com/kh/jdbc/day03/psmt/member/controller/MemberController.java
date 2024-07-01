package com.kh.jdbc.day03.psmt.member.controller;

import com.kh.jdbc.day03.psmt.member.model.dao.MemberDao;
import com.kh.jdbc.day03.psmt.member.model.vo.Member;

public class MemberController {
	
	MemberDao mDao;
		
		public MemberController() {
			mDao = new MemberDao();
		}
		
		public int registerMember(Member mOne) {
			int result = mDao.insertMember(mOne);
			return result;
		}

		public Member checkLogin(Member member) {
			Member result = mDao.selectOne(member);
			return result;
		}
		
		public Member checkMember(String memberId) {
			Member result = mDao.selectOne(memberId);
			return result;
		}

		public int deleteInfo(String memberId) {
			int result = mDao.deleteinfo(memberId);
			return result;
		}

		public int updateMember (Member memberId) {
			int result = mDao.updateMember(memberId);
			return result;
		}

		
}
