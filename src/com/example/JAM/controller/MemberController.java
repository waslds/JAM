package com.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.example.JAM.dto.Member;
import com.example.JAM.service.MemberService;
import com.example.JAM.session.Session;

public class MemberController {
	
	private Scanner sc;
	
	private MemberService memberService;
	
	public MemberController(Scanner sc, Connection conn) {
		this.sc = sc;
		this.memberService = new MemberService(conn);
	}
	
	public void doJoin() {
		System.out.println("== 회원 가입 ==");
		
		if (Session.isLogined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		String loginId = null;
		
		while (true) {
			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다");
				continue;
			}
			
			boolean isLoginIdDupChk = memberService.isLoginIdDupChk(loginId);
			
			if (isLoginIdDupChk) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다\n", loginId);
				continue;
			}
			
			System.out.printf("%s은(는) 사용가능한 아이디입니다\n", loginId);
			break;
		}
		
		String loginPw = null;
		String loginPwChk = null;
		
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력정보입니다");
				continue;
			}
			
			System.out.printf("비밀번호 확인 : ");
			loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				continue;
			}
			
			break;
		}
		
		String name = null;
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.length() == 0) {
				System.out.println("이름은 필수 입력정보입니다");
				continue;
			}
			
			break;
		}
		
		memberService.doJoin(loginId, loginPw, name);
		
		System.out.printf("%s회원님이 가입되었습니다\n", name);
	}

	public void doLogin() {
		System.out.println("== 로그인 ==");
		
		if (Session.isLogined()) {
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		String loginId = null;
		String loginPw = null;
		
		while (true) {
			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginId.length() == 0) {
				System.out.println("아이디는 필수 입력정보입니다");
				continue;
			}
			
			if (loginPw.length() == 0) {
				System.out.println("비밀번호는 필수 입력정보입니다");
				continue;
			}
			
			Member member = memberService.getMemberByLoginId(loginId);
			
			if (member == null) {
				System.out.printf("%s은(는) 존재하지 않는 아이디입니다\n", loginId);
				continue;
			}
			
			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			Session.login(member);
			
			System.out.printf("%s님 환영합니다~\n", member.name);
			
			break;
		}
	}

	public void doLogout() {
		if (Session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		Session.logout();
		System.out.println("로그아웃 되었습니다");
	}
}
