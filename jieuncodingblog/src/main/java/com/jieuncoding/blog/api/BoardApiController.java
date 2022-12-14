package com.jieuncoding.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jieuncoding.blog.auth.PrincipalDetail;
import com.jieuncoding.blog.dto.Board;
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

}
