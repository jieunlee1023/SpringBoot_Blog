package com.tencoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/user")
public class UserController {
	
	@Autowired
	 private HttpSession session;

	@GetMapping("/auth/login_form")
	public String loginForm() {
		//경로 : /WEB-INF/views/user/login_form.jsp;
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}
	
	@GetMapping("/user/update_form")
	public String updateForm() {
		return "user/update_form";
	}
	
	
// 기존 스프링에서 로그아웃 처리는 따로 정리 !
//	@GetMapping("/logout")
//    public String logout() {
//
//        HttpSession httpSession = session;
//        httpSession.invalidate(); // 로그아웃 처리
//
//        return "redirect:/";
//    }
	
}
