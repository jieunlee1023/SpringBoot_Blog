package com.jieuncoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jieuncoding.blog.dto.Board;
import com.jieuncoding.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable)

	{
		Page<Board> boards = boardService.getBoardList(pageable);
		model.addAttribute("boards", boards);

		return "index";
	}

	@GetMapping("/board/save-form")
	public String saveForm() {
		return "/board/save-form";
	}

}
