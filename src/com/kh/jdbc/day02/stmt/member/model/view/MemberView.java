package com.kh.jdbc.day02.stmt.member.model.view;

import java.util.List;
import com.kh.jdbc.day02.stmt.member.controller.MemberController;
import java.util.Scanner;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {
	// View 클래스에서 계속 쓸거니
	// 필드로 두고
	MemberController mController;

	public MemberView() {
		// 생성자에서 초기화 해줌
		mController = new MemberController();
	}

	public void startProgram() {
		int value = 0;
		end: while (true) {
			value = this.printMeinMenu();
			switch (value) {
			case 1:
				Member member = this.inputMember(); // inputMember()를 실행시켜 정보 받기
				// ID부터 취미까지 저장된 member객체를 컨트롤러로 전달
				mController.insertMember(member); // 매개변수로 member 전달
				break;
			case 2:
				// 2를 눌렀다면 회원의 전체 정보를 출력해야함
				// 1. 디비에서 데이터 가져오기, 전체 회원 정보니까 여러개, 여러개니까 List, 멤버니까 List<Member>
				List<Member> mList = mController.printAllMember();
				// 2. view의 메소드를 이용해서 출력하기
				this.printAllMember(mList);
			case 3:
				String memberId = this.inputMemberId();
				// 입력 받은 아이디로 디비에서 검색해 와야 되니까 printOneMember
				// 컨트롤러로 전달해야 되니까 printOneMember(memberId);
				member = mController.printOneMember(memberId);
				this.printOneMember(member);
				break;
			case 4 :
				// 4를 눌렀다면 회원의 정보를 수정해야 함(아이디로 정보가 존재하는지 확인 후 있으면 수정 없으면 안함)
				// 사용자가 수정할 아아디 입력 받아야 되니까 inputMember();
				memberId = inputMemberId();
//				존재하는 정보만 수정로직을 타야되니까 
				// memberId 전달해야 되니까 prontOneMember(memberid)
				// DB에서 가져온 값 저장해야 되니까 member
				member = mController.printOneMember(memberId);
				if (member != null) {
					// 수정할 때에는 수정할 정보를 입력해야 되니까 modifyMember(member)
					// 즉 수정할 정보를 가지고 있는 member 객체가 필요함
					Member modifyInfo = this.inputModifyInfo();
					// UPDATE 할때는 가장 중요한 것이 WHERE 조건절 이니까
					// WHERE에 들어갈 데이터를 전달 해줘야 함
					// modifyMember(member) 에서 member에 memberId를 꼭 넣어줘야하니까
					// member.setMemberID(memberId);
					modifyInfo.setMemberId(memberId);
					int result = mController.modifiMember(modifyInfo);
					if (result > 0) {
						this.displayMassage("수정 성공");
					} else {
						this.displayMassage("수정 실패");
					}
				}else {
					this.displayMassage("존재하지 않는 정보입니다.");
				}
				break;
			case 5:
				// 5를 눌렀다면 회원의 정보를 삭제해야함(아이디로 삭제)
				// 사용자가 삭제할 아아디 입력받아야 되니까 inputMember(0
				memberId = this.inputMemberId();
				// 존재하는 정보만 삭제로징을 타야되니까 printOneMember() 호출
				// memberId 전달해야되니까
				member = mController.printOneMember(memberId);
				if (member != null) {

					// 입력 받은 아이디로 디비에서 삭제해야되니까 removeMember();
					// 컨트롤러로 전달해야 되니까 removeMember(memberId);
					// DML의 결과는 int니까 int result
					int result = mController.removeMember(memberId);
					if (result > 0) {
						this.displayMassage("삭제 성공");
					} else {
						this.displayMassage("삭제 실패");
					}
				} else {
					this.displayMassage("존재하지 않는 정보입니다.");
				}
				break;
			case 0:
				this.displayMassage("프로그램을 종료합니다.");
				break end;
			}
		}
//		mController.insertMember();
//		List<Member> mList = mController.listMember();
//		this.displayMemberList(mList);
	}

	private Member inputModifyInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 정보 수정 =====");
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 위에서 입력한 enter를 밑에 nextLine에 안들어가게
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member = new Member(); // 여러개를 리턴못하니까 멤버 클래스 변수에 값을 넣는다
		member.setMemberPw(memberPw);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		member.setHobby(hobby);
		return member; 
	}

	private void printOneMember(Member member) {
		System.out.println("===== 회원 정보 출력(아이디로 검색) =====");
		System.out.printf(
				"이름 : %s\t 나이 : %d\t" + "\t 아이디 : %s\t 성별 : %s\t 이메일 : %s\t" + " 전화번호 %s\t 주소 : %s\t 취미 : %s\t"
						+ " 가입날짜 : %s\n",
				member.getMemberName(), member.getAge(), member.getMemberId(), member.getGender(), member.getEmail(),
				member.getPhone(), member.getAddress(), member.getHobby(), member.getRegDate());

	}

	// MemberView 35
	public void printAllMember(List<Member> mList) {
		System.out.println("===== 회원 정보 전체 출력 =====");
		for (Member member : mList) {
			System.out.printf(
					"이름 : %s\t 나이 : %d\t" + "\t 아이디 : %s\t 성별 : %s\t 이메일 : %s\t" + " 전화번호 %s\t 주소 : %s\t 취미 : %s\t"
							+ " 가입날짜 : %s\n",
					member.getMemberName(), member.getAge(), member.getMemberId(), member.getGender(),
					member.getEmail(), member.getPhone(), member.getAddress(), member.getHobby(), member.getRegDate());
		}
	}

	private void displayMassage(String msg) {
		System.out.println("서비스 결과 : " + msg);
	}

	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}

	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 정보 등록 =====");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 위에서 입력한 enter를 밑에 nextLine에 안들어가게
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member = new Member(); // 여러개를 리턴못하니까 멤버 클래스 변수에 값을 넣는다
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setGender(gender);
		member.setAge(age);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		member.setHobby(hobby);
		return member; // 멤버 클래스 참조변수니까 void가 아닌 Member 타입으로 반환

	}

	private int printMeinMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 관리 프로그램 =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 전체 회원 조회");
		System.out.println("3. 회원 조회(아이디로 검색)");
		System.out.println("4. 회원 정보수정");
		System.out.println("5. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice; // 다른 메소드에서 쓸 수 있도록 리턴
	}

}
