package com.tencoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * 작업단위 하나의 기능 + 하나의 기능들을 묶어서 단위의 기능을 처리 
	 * DB 수정시 RollBack (롤백) 처리도 가능하다.
	 * 
	 */

	// Spring DI - Bean 만들어두고 필요한 곳에서 Authowired를 사용하면 된다!
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public int saveUser(User user) {

		try {
			// 비밀번호를 넣을 때, 여기서 암호화 처리를 하고 DB에 저장하기
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			System.out.println("encPassword" + encPassword);			
			//$2a$10$q6KZ9Ye51isyj8l.V43icee8HB3PtwNxJKBqlDICUGyRzzH92Hg/6
			//$2a$10$yVLxvTp7v0MPNKPojQ7g6.J3sxq0Ba5zlOrlWduQdvucIRWoOFNVq
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);			
			userRepository.save(user);
			
			return 1;
		} catch (Exception e) {
		}

		return -1;
	}

//	public User login(User user) {
//		// 기본 Repository에 필요한 함수가 없을 경우 직접 생성하면 된다.
//
////		User userEntity = userRepository.findByUsernameAndPassword(
//		User userEntity = userRepository.login(user.getUsername(), user.getPassword());
//		System.out.println("userEntity : " + userEntity);
//
//		return userEntity;
//	}
	
	@Transactional
	public int updateUser(User requser) {
		User userEntity = userRepository.findById(requser.getId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
		}); 
		
		String rawPassword = requser.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		userEntity.setUsername(userEntity.getUsername());
		userEntity.setPassword(encPassword);
		userEntity.setEmail(requser.getEmail());
		//더티체킹 업데이트 시킬 예정
		
		return 1;
	}
	

}
