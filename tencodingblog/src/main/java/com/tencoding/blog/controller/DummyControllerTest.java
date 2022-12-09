package com.tencoding.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dummy")
public class DummyControllerTest {

	@Autowired // 순환 참조를 조심!!
	private UserRepository userRepository;

	// 1. 회원가입
	// http://localhost:9090/blog/dummy/signup
	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {

		// 로직수행
		// log.info(">>> User:{}",user);
		// user.setRole(RoleType.USER);
		// ...validation check
		userRepository.save(user);

		return "회원가입이 완료되었습니다!";
	}

	// 2. Select
	@GetMapping("/user/{id}")
	public User detail(@PathVariable int id) {
//		User user = userRepository.findById(id).orElseGet( () -> {
//			// orElseGet 커스텀한 데이터를 넣을 수 있다.
//			return new User();
//		});

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다 : " + id);
		});

		return user;
	}

	// 3. SelectAll
	@GetMapping("/user")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 4.Paging
	@GetMapping("/users2")
	public Page<User> pageList(@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {

		Page<User> userPage = userRepository.findAll(pageable);

		List<User> users = userRepository.findAll(pageable).getContent();
		return userPage;
	}

	// 5.회원수정
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {

		log.info(">>>id : {}, >>> password : {}, >>> email :{}", 
				id, reqUser.getPassword(), reqUser.getEmail());
		// 사용자 여부 먼저 확인 (select)
		// 사용자 있다면 넘겨 받은 데이터를 가공해서 저장
		// 사용자가 없다면 클라이언트에게 잘못된 요청, 없는 사용자 입니다.
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("잘못된 요청입니다. 수정에 실패하였습니다.");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
//		userRepository.save(user); //DB에 수정 후 저장
		
		return null;
	}
}
