package com.tencoding.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.model.RoleType;
import com.tencoding.blog.repository.UserRepository;

@Service // 스프링이 컨포넌트 스캔을 통해서 Bean으로 등록해준다(Ioc)
public class UserService {

	/*
	 * 서비스를 만드는 이유 트랜잭션 관리를 하기 위해서
	 */

	@Autowired
	private UserRepository userRepository;

	/*
	 * 작업단위 하나의 기능 + 하나의 기능들을 묶어서 단위의 기능을 처리 DB 수정시 RollBack (롤백) 처리도 가능하다.
	 * 
	 */

	@Transactional
	public int saveUser(User user) {

		try {
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return -1;
	}

	public User login(User user) {
		// 기본 Repository에 필요한 함수가 없을 경우 직접 생성하면 된다.

//		User userEntity = userRepository.findByUsernameAndPassword(
		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
		System.out.println("userEntity : " + userEntity);

		return userEntity;
	}

}
