package com.demo.mybatis.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.mybatis.model.dto.common.BoardDto;

@Mapper
public interface BoardDAO {
	public  int insert( @Param("board") BoardDto boardDto, @Param("userId") int userId);
	public List<BoardDto> selectAll();
	public BoardDto findById(int boardId);
	public int delete(int boardId);
}
