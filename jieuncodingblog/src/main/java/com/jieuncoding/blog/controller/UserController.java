package com.jieuncoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/auth/login-form")
	public String loginForm() {
		return "user/login-form";
	}

	@GetMapping("/auth/join-form")
	public String joinForm() {
		return "user/join-form";
	}
	
	@GetMapping("/user/update-form")
	public String updateForm() {
		return "user/update-form";
	}

}
