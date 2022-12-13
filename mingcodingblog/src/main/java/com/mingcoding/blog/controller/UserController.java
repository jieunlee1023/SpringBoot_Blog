package com.mingcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired
	private HttpSession Session;

	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}

	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}

	@GetMapping("/logout")
	public String logout() {
		HttpSession httpSession = Session;
		httpSession.invalidate();

		return "redirect:/";
	}

}
