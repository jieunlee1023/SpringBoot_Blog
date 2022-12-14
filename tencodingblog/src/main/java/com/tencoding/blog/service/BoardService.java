package com.tencoding.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.User;
import com.tencoding.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public void write(Board board, User user) {
		// 가독성을 위해 한번 더 지정
		board.setCount(0);
		board.setUserId(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}

}
