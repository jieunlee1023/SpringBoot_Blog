package com.mingcoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mingcoding.blog.dto.User;
import com.mingcoding.blog.model.RoleType;
import com.mingcoding.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public int saveUser(User user) {

		try {

			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			System.out.println("encPassword : " + encPassword);

			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);

			return 1;
		} catch (Exception e) {
		}
		return -1;
	}

}
