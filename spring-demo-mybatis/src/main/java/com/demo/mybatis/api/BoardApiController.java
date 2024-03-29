package com.demo.mybatis.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mybatis.model.dto.common.BoardDto;
import com.demo.mybatis.model.dto.common.User;
import com.demo.mybatis.model.dto.res.ResponseDto;
import com.demo.mybatis.service.BoardService;
import com.demo.mybatis.utils.Script;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

	private final BoardService boardService;

	@PostMapping("/save")
	public ResponseEntity<?> saveBoardProc(@RequestBody BoardDto boardDto, HttpSession reqSession) {

		User principal = (User) reqSession.getAttribute("principal");
		if (principal == null) {
			System.out.println("잘못된 접근입니다.");
		}

		//System.out.println("principal : " + principal);
		//System.out.println(boardDto.toString());
		int result = boardService.save(boardDto, principal.getId());

		ResponseDto<Integer> responseDto = new ResponseDto<Integer>(1, "글 등록 완료", result);
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping("/delete/{boardId}")
	public String deleteBoard(@PathVariable int boardId, HttpSession reqSession) {
		if (reqSession.getAttribute("principal") == null) {
			return Script.back("권한이 없습니다!");
		}
		int result = -1;
		BoardDto boardDto = boardService.findById(boardId);
		User principal = (User) reqSession.getAttribute("principal");
		if (principal.getUsername().equals(boardDto.getUsername())) {
			result = boardService.deleteBoard(boardId);
		} else {
			return Script.back("권한이 없습니다!");
		}

		if (result == 1) {
			return String.valueOf(result);
		}

		return String.valueOf(-1);
	}

	/**
	 * MINE TYPE : application/json
	 * 
	 * @param boardDto
	 * @param boardId
	 * @return 1 (성공), -1(실패)
	 */
	@PutMapping("/update/{boardId}")
	public ResponseEntity<?> updateBoardProc(@RequestBody BoardDto boardDto, @PathVariable int boardId,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User principal = null;
		int result = -1;
		if (session.getAttribute("principal") != null) {
			principal = (User) session.getAttribute("principal");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("잘못된 접근입니다!");
		}
		result = boardService.modifyBoard(boardDto);
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}

}
