package com.tencoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	//DI
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController 호출됨!");
		System.out.println("user: "+user);

		// 1 or -1 이 무조건 넘어온다.
		int result = userService.saveUser(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 object --> json 형식으로 변환
	}
}
