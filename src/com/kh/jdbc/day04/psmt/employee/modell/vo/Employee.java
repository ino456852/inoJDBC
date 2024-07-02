package com.kh.jdbc.day04.psmt.employee.modell.vo;

import java.sql.Date;

public class Employee {
	private String emId;
	private String emName;
	private String emNo;
	private String email;
	private String phone;
	private String deptCode;
	private String jobCode;
	private String sallecel;
	private int		salary;
	private double bonus;
	private String managerId;
	private Date hireDate;
	private Date emtDate;
	private String entYn;
	
	public Employee() {}
	
	public Employee(String emId, String emName, String emNo, String email, String phone, String deptCode,
			String jobCode, String sallecel, int salary, double bonus, String managerId, Date hireDate, Date emtDate,
			String entYn) {
		super();
		this.emId = emId;
		this.emName = emName;
		this.emNo = emNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.sallecel = sallecel;
		this.salary = salary;
		this.bonus = bonus;
		this.managerId = managerId;
		this.hireDate = hireDate;
		this.emtDate = emtDate;
		this.entYn = entYn;
	}
	public String getEmId() {
		return emId;
	}
	public void setEmId(String emId) {
		this.emId = emId;
	}
	public String getEmName() {
		return emName;
	}
	public void setEmName(String emName) {
		this.emName = emName;
	}
	public String getEmNo() {
		return emNo;
	}
	public void setEmNo(String emNo) {
		this.emNo = emNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getSallecel() {
		return sallecel;
	}
	public void setSallecel(String sallecel) {
		this.sallecel = sallecel;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getEmtDate() {
		return emtDate;
	}
	public void setEmtDate(Date emtDate) {
		this.emtDate = emtDate;
	}
	public String getEntYn() {
		return entYn;
	}
	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}
	
	@Override
	public String toString() {
		return "Employee [emId=" + emId + ", emName=" + emName + ", emNo=" + emNo + ", email=" + email + ", phone="
				+ phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", sallecel=" + sallecel + ", salary="
				+ salary + ", bonus=" + bonus + ", managerId=" + managerId + ", hireDate=" + hireDate + ", emtDate="
				+ emtDate + ", entYn=" + entYn + "]";
	}
	
	
}
