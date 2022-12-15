package com.tencoding.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	private AuthenticationManager authenticationManager;

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
		System.out.println("user : " + user);
		userService.updateUser(user);
		//////////////////////////////////////////////////////
		// 목표 : Authentication에 접근해서 담겨있는 Object 값을 수정해야 한다.
		//1. Authentication 객체 생성
		//2. AuthenticationManager 메모리에 올려서 authenticate 메서드에 Authentication 저장한다.
		//. UsernamePasswordAuthenticationToken을 생성해야한다.
		//3. SecurityContextHolder.getContext().setAuthentication(우리가 만든 Authentication ());
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK, 1);
	}

}
