package com.jieuncoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jieuncoding.blog.dto.User;
import com.jieuncoding.blog.model.RoleType;
import com.jieuncoding.blog.repository.UserRepository;

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
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);

			return 1;
		} catch (Exception e) {
		}

		return -1;
	}

	@Transactional
	public int updateUser(User user) {
		User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다.");
		});

		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);

		userEntity.setUsername(userEntity.getUsername());
		userEntity.setPassword(encPassword);
		userEntity.setEmail(user.getEmail());

		return 1;
	}

}
