package com.demo.mybatis.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.mybatis.model.dto.common.User;

@Mapper
public interface UserDAO {
	public int insert(User user);
	public List<User> findAll();
	public int deleteById(int userId);
	public int update(User user);
}
