package com.demo.mybatis.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mybatis.advice.UniqeUserNameException;
import com.demo.mybatis.model.dao.UserDAO;
import com.demo.mybatis.model.dto.common.User;
import com.demo.mybatis.model.dto.req.SigninDto;

@Service
public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// select에 걸어두는 것은 정합성 때문에 걸어둔다.
	@Transactional
	public List<User> findbyUserAll() {
		List<User> userList = userDAO.findAll();
		return userList;
	}

	/*
	 * Timestamp => 나노초까지 컨트롤 가능 Date => 밀리 세컨즈까지 처리 가능 UserDTO setCreateDate
	 * TimeStamp (포멧터 사용하지 않는 편안함!)
	 */

	@Transactional
	public int saveUser(User user) {
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		System.out.println(timestamp);
//		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		formater.format(timestamp);

		user.setRole("USER");
		user.setCreateDate(new Timestamp(System.currentTimeMillis()));

		int result = -1;
		try {
			result = userDAO.insert(user);
		} catch (Exception e) {
			String msg = "중복된 이름이 존재합니다.";
			String field = "username";
			String invalidValue = user.getUsername();
			throw new UniqeUserNameException(msg, field, invalidValue);
		}
		return result;
	}

	@Transactional
	public User findByUsername(SigninDto signinDto) {
		// 사용자 이름으로 찾는 기능 분리
		// 찾았다면 넘겨받은 비밀번호 조회된 user 패스가 같은지 확인해서 처리 해줘야 한다.
		User userEntity = userDAO.findByUsername(signinDto); //이름 여부만 확인
		
		if (userEntity == null) {
			return null;
		}
		
		if (userEntity.getPassword().equals(signinDto.getPassword())) {
			return userEntity;
		} else {
			return null;
		}
		
	}

	@Transactional
	public int deleteUser(int userId) {
		return userDAO.deleteById(userId);
	}

	@Transactional
	public int updateUser(User user) {
		return userDAO.update(user);
	}
}
