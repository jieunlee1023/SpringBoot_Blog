package com.jieuncoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jieuncoding.blog.auth.PrincipalDetail;
import com.jieuncoding.blog.dto.Board;
import com.jieuncoding.blog.dto.Reply;
import com.jieuncoding.blog.dto.User;
import com.jieuncoding.blog.repository.BoardRepository;
import com.jieuncoding.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRepository replyRepository;

	public void write(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	public Board boardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다..!");
		});
	}

	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public int modifyBoard(int boardId, Board board) {
		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		return 1;
	}

	public void writeReply(int boardId, Reply reply, User user) {

		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글은 없습니다.");
		});

		reply.setUser(user);
		reply.setBoard(board);

		replyRepository.save(reply);
	}

	public void deleteReplyById(int replyId, int id) {

		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다.");
		});

		try {
			int dbWriter = replyEntity.getUser().getId();
			int preicpalId = id;

			if (dbWriter == preicpalId) {
				replyRepository.deleteById(replyId);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("본인이 작성한 글이 아닙니다.");

		}

	}
	
	@Transactional
	public Page<Board> searchBoard(String q, Pageable pageable) {
		return boardRepository.findByTitleContaining(q,pageable);
	}

}
