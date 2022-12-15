package com.demo.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.server2.dto.User;

@RestController
@RequestMapping("/api/server")
public class ApiController {

	@GetMapping("/hello")
	public User serverHello() {

		User user = new User();
		user.setAge("10");
		user.setName("홍길동");

		return user;
	}
	

	@GetMapping("/hello1/{name}/name/{age}")
	public User serverHi(@PathVariable String name, @PathVariable String age) {

		System.out.println("name"+name);
		System.out.println("age"+age);
		
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}


	@GetMapping("/hello2")
	public User serverHello(@RequestParam String name, @RequestParam String age) {

		System.out.println("name"+name);
		System.out.println("age"+age);
		
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return user;
	}

}
