package com.kh.jdbc.day04.psmt.employee.model.servise;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day04.psmt.common.JDBCTemplate;
import com.kh.jdbc.day04.psmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day04.psmt.employee.modell.vo.Employee;

public class EmployeeService {

//	private JDBCTemplate jdbcTemplate;
	private EmployeeDAO eDao; // 포함상속

	public EmployeeService() {
//		jdbcTemplate = new JDBCTemplate();
		eDao = new EmployeeDAO();
	}

	public List<Employee> selectList() {
		Connection conn = null;
		List<Employee> eList = null;

		try {
			conn = JDBCTemplate.getConnection();
			eList = eDao.selectList(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}

	public int insertEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;

		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.insertEmployee(conn, emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.updateEmployee(conn, emp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmployee(String empId) {
		Connection conn = null;
		int result = 0;

		try {
			conn = JDBCTemplate.getConnection();
			result = eDao.deleteEmployee(conn, empId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Employee selectOnebyId(String empId) {
		Connection conn = null;
		Employee emp = null;

		try {
			conn = JDBCTemplate.getConnection();
			// JDBCTemplate의 메솓, getConnection 호출
			emp = eDao.selectOnebyId(conn, empId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}
}
