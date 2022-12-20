package com.jecoding.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jecoding.blog.dto.Board;
import com.jecoding.blog.dto.Reply;
import com.jecoding.blog.dto.User;
import com.jecoding.blog.repository.BoardRepository;
import com.jecoding.blog.repository.ReplyRepository;

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
	public Page<Board> getBoardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public Page<Board> searchBoard(String q, Pageable pageable){
		return boardRepository.findByTitleContaining(q, pageable);
	}
	@Transactional
	public Board boardDetail(int boardId) {
		return boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다!");
		});
	}
	
	@Transactional
	public Reply replyDetail(int replyId) {
		return replyRepository.findById(replyId).orElseThrow(()->{
			return new IllegalArgumentException("해당 글을 찾을 수 없습니다!");
		});
	}
	
	@Transactional
	public int modifyBoard(int boardId, Board board) {

		Board boardEntity = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("해당  글을 찾을 수 없습니다.");
		});
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		return 1;
	}
	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);
	}
	@Transactional
	public void writeReply(int boardId, Reply requestReply, User user) {
		
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기 실패 : 해당 글을 찾을 수 없습니다.");
		});
		
		requestReply.setUser(user);
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
		
	}

	@Transactional
	public void deleteReplyById(int replyId, int id) {
		
		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(()->{
			return new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.");
		});
		
		try {
			int dbWriter = replyEntity.getUser().getId();
			int principalId = id;
			
			if (dbWriter == principalId) {
				replyRepository.deleteById(replyId);
			} else {
				throw new IllegalAccessException("본인이 작성한 글이 아니빈다.");
			}
		} catch (Exception e) {
		}
		
	}

	@Transactional
	public int modifyReply(int replyId, Reply reply) {
		Reply replyEntity = replyRepository.findById(replyId).orElseThrow(()->{
			return new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.");
		});
		replyEntity.setContent(reply.getContent());
		return 1;
	}




}
