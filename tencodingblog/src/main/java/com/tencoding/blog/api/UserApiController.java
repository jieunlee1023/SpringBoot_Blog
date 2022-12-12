package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {

	// DI
	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@PostMapping("/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController save 호출됨!");
		System.out.println("user: " + user);

		// 1 or -1 이 무조건 넘어온다.
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 object --> json 형식으로 변환
	}

	@PostMapping("/user/login")
	public ResponseDto<?> login(@RequestBody User user) {
		System.out.println("UserApiController login 호출됨!");
		System.out.println("user: " + user);

		// principal 접근 주체
		User principal = userService.login(user);
		System.out.println("principal :" + principal);

		if (principal != null) {
			session.setAttribute("principal", principal);
		}

		return new ResponseDto<>(HttpStatus.OK, principal); // 자바 object --> json 형식으로 변환
	}

	
}
