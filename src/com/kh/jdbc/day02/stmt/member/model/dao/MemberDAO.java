package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// // JDBC 코딩이 있어야 함.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "INOJDBC";
	private final String PASSWORD = "INOJDBC";

	public Member selectOne(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		// select 하니까 ResultSet
		ResultSet rset = null;
		// try 안에서 쓴 변수는 return이 안되니까 try 밖에서 Member memeber = null;
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'";
			rset = stmt.executeQuery(query);
//			member = rsetToMember(rset);
			if(rset.next()) {
				member = rsetToMember(rset);
			}
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try 안에서 쓴 변수는 return 안되니까 try 밖에서 Member member = null
		// 호출한 곳에서 써야되니까 return member; MemberController 26
		return member;
	}
	// return 이 있으니까 void 대신 Member
	public Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		// 비어있으면 안되니까 setter
		// resultset에서 값을 가져와야 되니까 rset.getString("컬럼명");
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
		// member에 다 담았고 호출한 곳에서 써야하니까 return member;
		return member;
	}

	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록 2. DBMS 연결 생성 3. Statement 생성 4. 쿼리문 전송 5. 결과값 받기 6. 자원 해제
		 */
		// 1. 왜 mList에 member 가 들어가나요?
		// 2. rset은 mList에 못 들어가나요?
		// 3. rset을 member로 어떻게 바꾸나요?
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3 2 ResultSet의 getString(), getInt(), getDate() 필요
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// 디비에서 가져온 값 넘겨줘야 하니까 
		List<Member> mList = null; // try 안에 적을 필요 없음
		try {
			Class.forName(DRIVER_NAME);	// 드라이버 등록
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);	// 연결 생성
			 stmt = conn.createStatement(); // 워크시트 열기
			// 4. 쿼리문 작성,; 오타 조심 '홀따옴표 조심' 5. 결과값 받기
			String query = "SELECT * FROM MEMBER_TBL"; // 쿼리문 작성
			// 실행 CTRL + ENTER
			rset = stmt.executeQuery(query); // 결과값 받기
			// 후처리, 여러개니까 while, 전부 가져올때까지 돈다
			mList = new ArrayList<Member>();
			while (rset.next()) {
				// rset은 바로 못쓰니까 rsetToMember 
				// -- 입력한 데이터를 리스트화 시키는 작업 getter setter...
				Member member = new Member();
				// 비어있으면 안되니까 setter
				// resultset에서 값을 가져와야 되니까 rset.getString("컬럼명");
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
				// member에 다 담고 List에 담아야되니까
				mList.add(member);	// List의 메소드 add
			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 자원 해제해야 되니까 close
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 호출한 곳에서 써야되니까 return mList; MemberController 28
		return mList;

	}

	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록 2. DMMS 연결 생성 3. Statement 생성 4. 쿼리문 전송 5. 결과값 받기 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		try {
			// 1. 드라이버 등록 Class 메소드 forName
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결 생성 인터페이스 Connection 참조변수 conn 에 드라이버 메니저의 메소드 getConnection db 계정
			// 정보 입력
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 밑에서 커밋/롤백 할거니까 자동커밋 해제
			conn.setAutoCommit(false);
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 4. 쿼리문 전송
			String query = "INSERT INTO MEMBER_TBL VALUES('" 
					+ member.getMemberId() + "','" // 문자를 입력하려면 홀따옴표사이에 쌍따옴표 넣고 값 넣기
					+ member.getMemberPw() + "','" // 숫자는 홀따옴표를 안쓴다 sql 에서 NUMBER
					+ member.getMemberName() + "','" 
					+ member.getGender() + "'," 
					+ member.getAge() + ",'"
					+ member.getEmail() + "','" 
					+ member.getPhone() + "','" 
					+ member.getAddress() + "','"
					+ member.getHobby() + "',DEFAULT)";
			// 5. 결과값 받기
//			ResultSet rset = stmt.executeQuery(query);	// SELECT 할때만 ResultSet = select의 결과
			int result = stmt.executeUpdate(query); // 시험에 나올수도 있음 DML의 경우 호출하는 메소드
			// 후처리
			if (result > 0) { // 0보다 크면 성공 지금은 자동 커밋임
				// commit
				// 성공 메세지 출력
				System.out.println("데이터 등록 성공");
			} else {
				// rollback
				// 실패 메세지 출력
				System.out.println("데이터 등록 실패");
			}
			// 6. 다 쓴 자원 해제
//			stmt.close();
//			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 예외가 발생하든 안하든 무조건 실행
				// 자원 반납을 통해 오류 발생 방지
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int updateMember(Member modifyInfo) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			// 1 드라이버 등록
			// 
			Class.forName(DRIVER_NAME);
			// 2, 연결 생성
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			conn.setAutoCommit(false);
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 문자열은 ''로 감싸야 되니까 '""'
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = '"
			+modifyInfo.getMemberPw()+"',EMAIL = '"
			+modifyInfo.getEmail()+"', PHONE = '"
			+modifyInfo.getPhone()+"',ADDRESS = '"
			+modifyInfo.getAddress()+"',HOBBY = '"
			+modifyInfo.getHobby()+"'WHERE MEMBER_ID = '"
			+modifyInfo.getMemberId()+"'";
			// 쿼리문 전송 및 5. 결과 받기
			result = stmt.executeUpdate(query);
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
		}return result;
	} 
	public int deleteMember(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		try {
			// 1. 드라이버 등록
			
			Class.forName(DRIVER_NAME);
			// 2. 연결 생성
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
		
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 쿼리문 작성
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '" +memberId+"'";
			// 4. 쿼리문 전송 및 5. 결과 받기
			// DML 결과값은 성공한 행의 갯수니까 int result
			// 쿼리 실행 메소드는 DML이니까 executeUpdate(query)
			result = stmt.executeUpdate(query);
			// 쿼리 설공하면 커밋, 실패하면 롤백해야되니까 if(result > 0)
			if (result > 0) {
				// 커밋
				conn.commit();
			} else {
				// 롤백
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// try 안에서 쓴 변수는 return 안되니까 try 밖에서 int result = 0;
		// 호출한 곳에서 써야되니까 return member, MemerController
		
		return result;
	}
}
