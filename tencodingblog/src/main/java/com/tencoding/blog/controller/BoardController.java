package com.tencoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.dto.Reply;
import com.tencoding.blog.dto.ResponseDto;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	/*
	 * 로그인이 인증되면 컨트롤러에서 어떻게 세션을 찾을까요 ?
	 */

	@Autowired
	private BoardService boardService;

	// ?page =2
	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {

		Page<Board> boards = boardService.getBoardList(pageable);
		model.addAttribute("boards", boards);
		// jsp 파일에서 model 추상객체를 이용해서 컨트롤러에서 내려 준 데이터에 접근이 가능하다.

		return "index";
	}
	// page. first = true, false <--첫번째 페이지면 true
	// page. last = true, false <-- 마지막 페이지면 ture

	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}

	@GetMapping("/board/{id}")
	public String showDetial(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}

	@GetMapping("/board/{id}/update_form")
	public String updateForm(@PathVariable(name = "id") int boardId, Model model) {
		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/update_form";
	}


}
