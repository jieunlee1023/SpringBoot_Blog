package com.jecoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jecoding.blog.auth.PrincipalDetail;
import com.jecoding.blog.dto.Board;
import com.jecoding.blog.dto.Reply;
import com.jecoding.blog.dto.ResponseDto;
import com.jecoding.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) {

		boardService.write(board, detail.getUser());
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/board/{boardId}")
	public ResponseDto<?> update(@PathVariable int boardId, @RequestBody Board board) {
		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<?> delete(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<?> replySave(@PathVariable int boardId, @RequestBody Reply requestReply,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		boardService.writeReply(boardId, requestReply, principalDetail.getUser());
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<?> replyDelete(@PathVariable int boardId, @PathVariable int replyId,
			@AuthenticationPrincipal PrincipalDetail detail) {

		try {
			boardService.deleteReplyById(replyId, detail.getUser().getId());

		} catch (Exception e) {
		}
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<?> updateReply(@PathVariable int boardId, @PathVariable int replyId, @RequestBody Reply reply){
		boardService.modifyReply(replyId, reply);
		return new ResponseDto<>(HttpStatus.OK, 1);
	}
}
