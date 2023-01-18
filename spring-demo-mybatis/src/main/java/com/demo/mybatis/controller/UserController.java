package com.demo.mybatis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.mybatis.model.dto.common.User;
import com.demo.mybatis.model.dto.req.SigninDto;
import com.demo.mybatis.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	//DI 처리
	private final UserService userService;
	private final HttpSession session;
	
	/**
	 *로그인 페이지 전환  
	 *인증 처리 X (public)
	 */
	
	@GetMapping("/signin-form")
	public String sigininForm() {
		return "user/signin_form";
	}

	/**
	 *회원가입 페이지 전환 
	 *인증 처리 X (public)
	 */
	
	@GetMapping("/signup-form")
	public String singupForm() {
		return "user/signup_form";
	}
	
	
	/**
	 * 회원가입 처리
	 * MINE TYPE - form(application/x-www-form-urlencoded)
	 * 파싱전략 : object mapper 사용
	 */
	
	@PostMapping("/signup-proc")
	public String signupProc(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}
	
	/**
	 * 인증 불필요
	 * 로그인 처리
	 * MINE TYPE - form(application/x-www-form-urlencoded)
	 * 파싱전략 : object mapper 사용
	 */
	
	@PostMapping("/signin-proc")
	public String signinProc(SigninDto signinDto, Model model) {
		User principal= userService.findByUsername(signinDto);
		if(principal == null) {
			model.addAttribute("isNotSignin",true);
			return "user/signin_form";
		}
		//  세션은 key, value로 데이터를 저장 할 수 있다.
		//principal.setPassword(null);
		session.setAttribute("principal", principal);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
