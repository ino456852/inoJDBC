package com.kh.jdbc.day04.psmt.employee.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.day04.psmt.employee.modell.vo.Employee;

public class EmployeeDAO {
	private final String FILE_NAME = "resources/query.properties";
	private Properties prop;
	
	public EmployeeDAO() {
		prop = new Properties();
		try {
			Reader reader = new FileReader(FILE_NAME);
			prop.load(reader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "INOJDBC";
	private final String PASSWORD = "INOJDBC";
	// 바깥에 반복되는 코드 선언
	
	public List<Employee> selectList(Connection conn) {
//		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Employee> eList = null;
		
		try {
			Class.forName(DRIVER_NAME);
//			conn = new JDBCTemplate().getConnection();
			String query = prop.getProperty("selectList");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// rsetToEmployee
			eList = new ArrayList<Employee>();
			while(rset.next()) {
				Employee emp = rsetToEmployee(rset);
				eList.add(emp);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();	// JDBCTemplate에서 conn.isclosed()이 있어서 안지워도 됨
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eList;
	}

	private Employee rsetToEmployee(ResultSet rset) throws SQLException {
		Employee emp = new Employee();
		emp.setEmId(rset.getString("EMP_ID"));
		emp.setEmName(rset.getString("EMP_NAME"));
		emp.setEmNo(rset.getString("EMP_NO"));
		emp.setEmail(rset.getString("EMAIL"));
		emp.setPhone(rset.getString("PHONE"));
		emp.setDeptCode(rset.getString("DEPT_CODE"));
		emp.setJobCode(rset.getString("JOB_CODE"));
		emp.setSallecel(rset.getString("SAL_LEVEL"));
		emp.setSalary(rset.getInt("SALARY"));
		emp.setBonus(rset.getDouble("BONUS"));
		emp.setManagerId(rset.getString("MANAGER_ID"));
		emp.setHireDate(rset.getDate("HIRE_DATE"));
		emp.setEmtDate(rset.getDate("ENT_DATE"));
		emp.setEntYn(rset.getString("ENT_YN"));
		return emp;
	}

	public int insertEmployee(Connection conn, Employee emp) throws SQLException {
//		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertEmployee");
		pstmt = conn.prepareStatement(query);						// 쿼리문 오타 조심하기!!
		pstmt.setString(1, emp.getEmId());
		pstmt.setString(2, emp.getEmName());
		pstmt.setString(3, emp.getEmNo());
		pstmt.setString(4, emp.getJobCode());
		pstmt.setString(5, emp.getSallecel());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	public int deleteEmployee(Connection conn, String empId) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteEmployee");
		pstmt = conn.prepareStatement(query);
		
		pstmt.setString(1, empId);	// 1번째 ?에 넣을 값, 사번이 있는지 확인
		result = pstmt.executeUpdate();	// DDL : INSERT,DELETE, UPDATE
		pstmt.close();
		return result;
	}

	public Employee selectOnebyId(Connection conn, String empId) throws SQLException {	
		// Service에 트라이 캐치가 있어서 예외처리 던지기
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp = null;
		String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, empId);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			emp = rsetToEmployee(rset);
		}
		pstmt.close();
		rset.close();
		return emp;
	}

	public int updateEmployee(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateEmployee");
		// 쿼리문 작성
		pstmt = conn.prepareStatement(query);

		
//		쿼리문에 입력할 값
		pstmt.setString(1, emp.getEmail());
		pstmt.setString(2, emp.getPhone());
		pstmt.setString(3, emp.getDeptCode());
		pstmt.setInt(4, emp.getSalary());
		pstmt.setDouble(5, emp.getBonus());
		pstmt.setString(6, emp.getManagerId());
		pstmt.setString(7, emp.getEmId());
		
		result = pstmt.executeUpdate();	// DDL : insert,update,delete
		pstmt.close();
		return result;
	}

}
