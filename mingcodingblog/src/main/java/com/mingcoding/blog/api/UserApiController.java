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
public class UserApiController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {

		System.out.println("회원가입 호출됨 !");
		System.out.println("user:" + user);

		int result = userService.saveUser(user);

		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}

}
