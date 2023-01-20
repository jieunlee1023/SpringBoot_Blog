package com.demo.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mybatis.model.dao.BoardDAO;
import com.demo.mybatis.model.dto.common.BoardDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardDAO boardDAO;

	@Transactional
	public int save (BoardDto boardDto, int userId) {
		return boardDAO.insert(boardDto, userId);
	}
	
	@Transactional
	public List<BoardDto> selectAll(){
		return boardDAO.selectAll();
	}
	
	@Transactional
	public BoardDto findById(int boardId){
		return boardDAO.findById(boardId);
	}

	@Transactional
	public int deleteBoard(int boardId) {
		return boardDAO.delete(boardId);
	}
	

	@Transactional
	public int modifyBoard(BoardDto boardDto) {
		return boardDAO.modifyByBoardId(boardDto);
	}
	
	
	
}

