package com.kh.jdbc.day04.psmt.employee.controller;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.day04.psmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day04.psmt.employee.model.servise.EmployeeService;
import com.kh.jdbc.day04.psmt.employee.modell.vo.Employee;

public class EmployeeController {
	
	private EmployeeService eServise;
//	private EmployeeDAO eDao;
	
	public EmployeeController() {
		eServise = new EmployeeService();
//		eDao = new EmployeeDAO();
	}

	public List<Employee> printAllemp() {
		List<Employee> eList = eServise.selectList();
//		List<Employee> eList = eDao.selectList();
		return eList;
	}

	public int insertEmployee(Employee emp) {
//		int result = eDao.insertEmployee(emp);
		int result = eServise.insertEmployee(emp);
		return result;
	}

	public int deleteEmploye(String empId) {
		int result = eServise.deleteEmployee(empId);	// eServise에서 가져온 값을 리턴
		return result;
	}

	public Employee selectOnebyId(String empId) {
		Employee emp = eServise.selectOnebyId(empId);
		return emp;
	}

	public int updateEmployee(Employee emp) {	
		int result = eServise.updateEmployee(emp);
		return result;
	}

}
