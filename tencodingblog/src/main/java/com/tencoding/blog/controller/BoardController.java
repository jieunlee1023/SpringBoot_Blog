package com.tencoding.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tencoding.blog.auth.PrincipalDetail;
import com.tencoding.blog.dto.Board;
import com.tencoding.blog.service.BoardService;

@Controller
public class BoardController {

	/*
	 * 로그인이 인증되면 컨트롤러에서 어떻게 세션을 찾을까요 ?
	 */

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {

		Page<Board> boards = boardService.getBoardList(pageable);
		model.addAttribute("boards", boards);
		// jsp 파일에서 model 추상객체를 이용해서 컨트롤러에서 내려 준 데이터에 접근이 가능하다.

		return "index";
	}

	@GetMapping("/board/save_form")
	public String saveForm() {
		return "/board/save_form";
	}

}
