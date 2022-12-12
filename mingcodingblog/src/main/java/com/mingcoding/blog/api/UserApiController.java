package com.mingcoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mingcoding.blog.dto.ResponseDto;
import com.mingcoding.blog.dto.User;
import com.mingcoding.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserApiController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	@PostMapping("/user")
	public ResponseDto<Integer> save(@RequestBody User user) {

		System.out.println("회원가입 호출됨 !");
		System.out.println("user:" + user);

		int result = userService.saveUser(user);

		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}

	@PostMapping("/user/login")
	public ResponseDto<?> login(@RequestBody User user) {
		System.out.println("로그인 호출됨");
		System.out.println("user : " + user);

		User principal = userService.login(user);
		System.out.println("principal:" + principal);
		if (principal != null) {
			session.setAttribute("principal", principal);	
			return new ResponseDto<Integer>(HttpStatus.OK, 1);
		}
		return  new ResponseDto<Integer>(HttpStatus.BAD_REQUEST, -1);
	}
}
