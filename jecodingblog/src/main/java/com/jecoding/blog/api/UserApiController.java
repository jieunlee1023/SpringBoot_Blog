package com.jecoding.blog.api;

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

import com.jecoding.blog.dto.ResponseDto;
import com.jecoding.blog.dto.User;
import com.jecoding.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<?> save(@RequestBody User user) {
		userService.saveUser(user);
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/user")
	public ResponseDto<?> update(@RequestBody User user) {
		
		userService.updateUser(user);
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

}
