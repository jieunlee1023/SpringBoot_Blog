package com.demo.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.mybatis.model.dto.common.User;
import com.demo.mybatis.service.UserService;
import com.demo.mybatis.utils.Script;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {

	private final UserService userService;
	
	@GetMapping("/req-test")
	@ResponseBody
	public String reqTest() {
		return Script.back("권한이  없습니다.");
	}

	@GetMapping("/user-list")
	@ResponseBody
	public List<User> userList() {
		// 서비스를 통해서 유저 리스트를 요청
		List<User> userList = userService.findbyUserAll();
		return userList;
	}

	@PostMapping("/user-save")
	@ResponseBody
	public int insertUserTest(@RequestBody User user) {
		int resultRow = userService.saveUser(user);
		return resultRow;
	}

	@DeleteMapping("/user-delete/{userId}")
	@ResponseBody
	public int deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}

	@PutMapping("/user-update")
	@ResponseBody
	public int updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

}
