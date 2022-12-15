package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.service.UserService;

@RestController
public class UserApiController {

	// DI
	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController save 호출됨!");
		System.out.println("user: " + user);

		// 1 or -1 이 무조건 넘어온다.
		int result = userService.saveUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, result); // 자바 object --> json 형식으로 변환
	}

//	@PostMapping("/user/login")
//	public ResponseDto<?> login(@RequestBody User user) {
//		System.out.println("UserApiController login 호출됨!");
//		System.out.println("user: " + user);
//
//		// principal 접근 주체
//		User principal = userService.login(user);
//		//System.out.println("principal :" + principal);
//
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//
//		return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바 object --> json 형식으로 변환
//	}

	
	@PutMapping("/api/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		
		//여기까지 오기전에 벨리데이션 처리... 아니면 예외 잡아서 사용자에게 떨궈 주면 된다.
		System.out.println("111111111111111111111111111111");
		System.out.println("user : " + user);
		userService.updateUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}

}
