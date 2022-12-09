package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login-form")
	public String loginForm() {
		//경로 : /WEB-INF/views/user/login_form.jsp;
		return "user/login_form";
	}
	
	@GetMapping("/join-form")
	public String joinForm() {
		return "user/join_form";
	}
	
	
}
