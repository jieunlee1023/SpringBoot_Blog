package com.jieuncoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jieuncoding.blog.auth.PrincipalDetail;
import com.jieuncoding.blog.dto.Board;
import com.jieuncoding.blog.dto.Reply;
import com.jieuncoding.blog.dto.ResponseDto;
import com.jieuncoding.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail detail) {

		boardService.write(board, detail.getUser());
		return new ResponseDto<>(HttpStatus.OK, 1);

	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.deleteById(id);
		return new ResponseDto<>(HttpStatus.OK, 1);
	}

	@PutMapping("/api/board/{boardId}")
	public ResponseDto<Integer> update(@PathVariable int boardId, 
			@RequestBody Board board){
		int result = boardService.modifyBoard(boardId, board);
		return new ResponseDto<>(HttpStatus.OK, result);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId,
			@RequestBody Reply reply,
			@AuthenticationPrincipal PrincipalDetail principalDetail){
		
		System.out.println("여긴 들어오니??????????????????");
		boardService.writeReply(boardId, reply,principalDetail.getUser());
		
		return new ResponseDto<>(HttpStatus.OK,1);
	}
}
