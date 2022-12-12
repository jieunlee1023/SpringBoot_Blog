package com.mingcoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingcoding.blog.dto.User;
import com.mingcoding.blog.model.RoleType;
import com.mingcoding.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public int saveUser(User user) {

		try {
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
		}
		return -1;
	}

	public User login(User user) {
		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
		System.out.println("로그인 userEntity:" + userEntity);
		return userEntity;
	}
}
