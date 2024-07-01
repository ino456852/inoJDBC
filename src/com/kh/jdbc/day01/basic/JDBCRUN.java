package com.kh.jdbc.day01.basic;

import java.sql.*;

public class JDBCRUN {
	public static void mein(String [] args) {
		/*
		 * JDBC 코딩 절차
		 * 1. 드라이버 등록 (jar)
		 * 2. DBMS 연결 생성 (KH/KH 확인)
		 * 3. Statement 객체 생성 (워크시트, 쿼리문 실행준비)
		 * 4. SQL 전송			  (CTRL + ENTER)
		 * 5. 결과받기			  (ResultSet)
		 * 6. 자원해제
		 * 
		 */
		// 1. 드라이버 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		// 2. DBMS 연결 생성
			Connection conn = 
						DriverManager.getConnection("jdbc:oracle.thin:@localhost:1521:xe"
					, "INOJDBC", "INOJDBC");	// 계정의 주소 아이디 비번
		// 3. Statement 객체 생성
			Statement stmt = conn.createStatement();	
		// 4. SQL 전송, 5. 결과받기
			String query = "SELECT * FROM EMPLOYEE";
			ResultSet rset = stmt.executeQuery(query);	// 결과 받기
		// 후처리
			while(rset.next()) {
				System.out.println("직원명 : " + rset.getString("EMP_ID")); // 컬럼명 오타없이 적기
				System.out.println("직원명 : " + rset.getString("EMP_NAME"));
			}
		// 6. 자원 해제
			rset.close();
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
