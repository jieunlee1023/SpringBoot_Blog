package com.demo.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mybatis.model.dao.UserDAO;
import com.demo.mybatis.model.dto.common.User;

@Service
public class UserService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	public List<User> findbyUserAll() {
		List<User> userList = userDAO.findAll();
		return userList;
	}

	//select에 걸어두는 것은 정합성 때문에 걸어둔다.
	@Transactional
	public int saveUser(User user) {
		int result = userDAO.insert(user);
		return result;
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
