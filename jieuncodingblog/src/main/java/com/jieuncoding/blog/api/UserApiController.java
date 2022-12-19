package com.jieuncoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jieuncoding.blog.dto.ResponseDto;
import com.jieuncoding.blog.dto.User;
import com.jieuncoding.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController save 호출됨");
		System.out.println("user : " + user);

		int result = userService.saveUser(user);
		return new ResponseDto<>(HttpStatus.OK, result);
	}
	
	@PutMapping("/api/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		
		System.out.println("여기들어완?????????????????????????????");
		userService.updateUser(user);
		
		return new ResponseDto<>(HttpStatus.OK, 1);
		
	}

}
