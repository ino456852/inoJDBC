package com.kh.jdbc.day03.psmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.jdbc.day03.psmt.member.model.vo.Member;

public class MemberDao {
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "INOJDBC";
	private final String PASSWORD = "INOJDBC";
			
	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			result = pstmt.executeUpdate();
			
			// Statement 사용
//			stmt = conn.createStatement();
//			String query = "INSERT INTO MEMBER_TBL VALUES('"
//			+member.getMemberId()+"','"
//			+member.getMemberPw()+"','"
//			+member.getMemberName()+"','"
//			+member.getGender()+"',"
//			+member.getAge()+",'"
//			+member.getEmail()+"','"
//			+member.getPhone()+"','"
//			+member.getAddress()+"','"
//			+member.getHobby()+"',DEFAULT)";
//			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Member selectOne(Member member) {
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member result = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 쿼리문을 그대로 실행하는 Statement와는 다르게
			// 쿼리문을 이용하여 컴파일을 미히하여 객체를 생성함
			// 쿼리문에는 값이 들어가는 자리를 위치홀더로 표시 해줘야함
			
			// PreparedStatement 사용 PreparedStatement 
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();
			
			// Statement 사용
//			stmt = conn.createStatement();
//			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"
//					+member.getMemberId()+"' AND MEMBER_PW = '"
//					+member.getMemberPw()+"'";
//			rset = stmt.executeQuery(query);
			
			// rsetToMember
			if(rset.next()) 
				result = rsetToMember(rset);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {	
		// 호출한 곳으로 던진다 던진곳에 try catch가 있어서 여기선 안감싸줘도 됨 
		Member member = new Member();
		String memberId = rset.getString("MEMBER_ID");
		member.setMemberId(memberId);
		member.setMemberPw(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER"));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setRegDate(rset.getDate("REG_DATE"));
		return member;
	}

	public int deleteinfo(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member result = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// PreparedStatement 사용 PreparedStatement 
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			// rsetToMember
			if(rset.next()) 
				result = rsetToMember(rset);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateMember(Member memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW =?,EMAIL =?,PHONE =?,ADDRESS =?, HOBBY =? WHERE MEMBER_ID =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId.getMemberPw());
			pstmt.setString(2, memberId.getEmail());
			pstmt.setString(3, memberId.getPhone());
			pstmt.setString(4, memberId.getAddress());
			pstmt.setString(5, memberId.getHobby());
			pstmt.setString(6, memberId.getMemberId());
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
