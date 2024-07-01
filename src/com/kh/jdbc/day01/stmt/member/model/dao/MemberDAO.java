package com.kh.jdbc.day01.stmt.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// // JDBC 코딩이 있어야 함.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "INOJDBC";
	private final String PASSWORD = "INOJDBC";
	
	
	public Member selectOne(String memberId) {
		Member member = new Member();
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원 해제
		 */
		try {
			Class.forName(DRIVER_NAME);
			Connection conn
			= DriverManager.getConnection(URL,USERNAME,PASSWORD);
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID =  '"+ memberId+ "'"; // 입력한 id
			ResultSet rset = stmt.executeQuery(query);	// 위 코드를 rset에 넘김
			if(rset.next()) { 		// rset 값이 있으면 true
				// rsetToMember
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PW"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setRegDate(rset.getDate("REG_DATE"));
			}
			rset.close();
			conn.close();
			stmt.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return member;
	}
	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원 해제
		 */
		// 1. 왜 mList에 member 가 들어가나요?
		// 2. rset은 mList에 못 들어가나요?
		// 3. rset을 member로 어떻게 바꾸나요?
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3 2 ResultSet의 getString(), getInt(), getDate() 필요
		List<Member> mList = new ArrayList<Member>(); 	// try 안에 적을 필요 없음
		try {
			Class.forName(DRIVER_NAME);
		// 2.
			Connection conn =
					DriverManager.getConnection(URL,USERNAME,PASSWORD);
		// 3.
			Statement stmt = conn.createStatement();
		// 4. 5.
			String query = "SELECT * FROM MEMBER_TBL";
			ResultSet rset = stmt.executeQuery(query);
		// 세팅 후 처리
			while(rset.next()) {
				// rsetToMember -- 입력한 데이터를 리스트화 시키는 작업 getter setter...
				Member member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPw(rset.getString("MEMBER_PW"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER"));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setRegDate(rset.getDate("REG_DATE"));
				mList.add(member);
			}
			
		// 6.
			conn.close();
			stmt.close();
			rset.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
		
	}
	
	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. DMMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		try {
			// 1. 드라이버 등록 Class 메소드 forName
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결 생성 인터페이스 Connection 참조변수 conn 에 드라이버 메니저의 메소드 getConnection db 계정 정보 입력  
			Connection conn = 
					DriverManager.getConnection(URL,USERNAME,PASSWORD);
			// 3. Statement 생성
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 전송
			String query = "INSERT INTO MEMBER_TBL VALUES('"
							+member.getMemberId()+"','"
							+member.getMemberPw()+"','"
							+member.getMemberName()+"','"
							+member.getGender()+"',"
							+member.getAge()+",'"
							+member.getEmail()+"','"
							+member.getPhone()+"','"
							+member.getAddress()+"','"
							+member.getHobby()+"',default)";
			// 5. 결과값 받기
//			ResultSet rset = stmt.executeQuery(query);	// SELECT 할때만 ResultSet = select의 결과
			int result = stmt.executeUpdate(query); 	// 시험에 나올수도 있음 DML의 경우 호출하는 메소드
			// 후처리
			if (result > 0 ) {
				// 성공 메세지 출력
				System.out.println("데이터 등록 성공");
				// commit
			}else {
				// 실패 메세지 출력
				System.out.println("데이터 등록 실패");
				// rollback
			}
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
