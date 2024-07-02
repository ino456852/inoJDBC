package com.kh.jdbc.day04.psmt.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.psmt.employee.controller.EmployeeController;
import com.kh.jdbc.day04.psmt.employee.modell.vo.Employee;

public class EmployeeView {
	
	EmployeeController empController;	// 컨트롤러에 값을 전달하기 위해 참조변수 선언
	
	public EmployeeView() {
		empController = new EmployeeController();	// Run에서 EmployeeView 호출할때마다 초기화됨
	}

	public void startApp() {
		end :
		while(true) {
			int menu = mainMenu();
			switch(menu) {
			case 1 : 
				List<Employee> eList = empController.printAllemp();
				// 전체 정보 출력 하기위해 List 참조변수 eList 선언후 
				// Controller,Service 를 거쳐 
				// 최종적으로 mDao 에서 rset을 통해 값을 받아온걸 eList에 저장
				this.showAllEmp(eList);
				// 받아온 eList값의 틀을 만들어서 전체 출력
				break;
			case 2 :
				// 사원 등록
				Employee emp = inputEmpInfo(); // 입력할 정보를 참조변수 emp에 넣기
				int result = empController.insertEmployee(emp);
				// int 타입에 넣을거니까 
				if(result > 0) {	// 값이 들어갔으면 1 이상
					System.out.println("등록 성공");
				}else {
					System.out.println("등록 실패");
				}
				break;
			case 3 :
				// 사원 정보 수정
				String empId = inputEmpId();	
				// 사번 입력 받아서 empId에 넣기
				emp = empController.selectOnebyId(empId); 
				// 입력받은 사번이 있는지 mDao에서 확인 후 emp에 저장
				if (emp != null) {	// emp 값이 있으면
					emp = modufyEmpInfo(); // 수정값을 emp에 저장
					emp.setEmId(empId); // 입력받은 사번을 emp에 저장
					// 정보 수정
					result = empController.updateEmployee(emp);	
					if(result > 0) {	// 값이 넘어오면 result가 1이상임
						printMsg("수정 성공");
					}
				} else {
					printMsg("존재하지 않는 정보입니다.");
				}
				break;
			case 4 : 
				empId = inputEmpId();
				result = empController.deleteEmploye(empId);
				if(result > 0) {
					System.out.println("삭제 성공");
				}else {
					System.out.println("삭제 실패");
				}
				break;
			case 0 :
				printMsg("프로그램이 종료되었습니다.");
				break end;
			}
		}
	}

	private Employee modufyEmpInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 사원 정보 수정 =====");
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("부서코드 : ");
		String deptCode = sc.next();
		System.out.print("급여 : ");
		int salary = sc.nextInt();
		System.out.print("보너스율 : ");
		double bonus = sc.nextDouble();
		System.out.print("관리자 : ");
		String managerId = sc.next();
		Employee emp = new Employee();
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setDeptCode(deptCode);
		emp.setSalary(salary);
		emp.setBonus(bonus);
		emp.setManagerId(managerId);
		return emp;
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 사원 관리 프로그램 =====");
		System.out.println("1. 사원 전체 출력");
		System.out.println("2. 사원 등록");
		System.out.println("3. 사원 정보 수정");
		System.out.println("4. 사원 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}

	private String inputEmpId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("사번 입력 : ");
//		String empId = sc.next();	  변수없이 입력받기
		return sc.next();
	}

	private Employee inputEmpInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 사원 정보 등록 =====");
		System.out.print("사번 : ");
		String enpId = sc.next();
		System.out.print("사원명 : ");
		String empName = sc.next();
		System.out.print("주민번호 : ");
		String empNo = sc.next();
		System.out.print("직급코드 : ");
		String jobCode = sc.next();
		System.out.print("급여등급 : ");
		String sallevel = sc.next();
		Employee emp = new Employee();
		emp.setEmId(enpId);
		emp.setEmName(empName);
		emp.setEmNo(empNo);
		emp.setJobCode(jobCode);
		emp.setSallecel(sallevel);
		return emp;
	}

	private void showAllEmp(List<Employee> eList) {
		System.out.println("===== 사원 정보 전체 출력 =====");
		for(Employee emp : eList) {
			System.out.printf("직원명 : %s, 사번 : %s, 이메일 : %s, 전화번호 : %s, 입사일자 : %s, 퇴직여부 : %s\n"
					,emp.getEmName(),emp.getEmId()
					,emp.getEmail(),emp.getPhone()
					,emp.getHireDate(),emp.getEntYn());
		}
	}

	private void printMsg(String msg) {
		System.out.println("[서비스 결과] : "+ msg);
	}

}
