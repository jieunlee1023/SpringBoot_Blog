package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {
		
		log.info(">>> User : {}",user);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입되었습니다!";
	}
	
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {
		//1. null로 처리하기
//		User user = userRepository.findById(id).orElseGet(()->{
//			return new User();
//		});
//		return user;
		
		//2. Exception으로 처리하기
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id가 "+id+"번인 유저는 존재하지 않습니다.");
		});
		return user;
		
	}
	
	
}
