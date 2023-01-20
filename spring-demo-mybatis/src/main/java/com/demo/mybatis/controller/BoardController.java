package com.demo.mybatis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.mybatis.model.dto.common.BoardDto;
import com.demo.mybatis.model.dto.common.User;
import com.demo.mybatis.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller

@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	// list
	@GetMapping({ "", "/", "/board/list" })
	public String list(Model model) {
		List<BoardDto> list = boardService.selectAll();
		model.addAttribute("boardList", list);
		return "board/main";
	}

	// 글쓰기화면
	@GetMapping("/auth/board/write-form")
	public String writeForm() {
		return "board/write_form";
	}

	@GetMapping("/board/detail/{boardId}")
	public String detail(@PathVariable(name = "boardId") int boardId, Model model, HttpSession reqSession) {

		BoardDto boardDto = boardService.findById(boardId);

		User principal = (User) reqSession.getAttribute("principal");
		boolean isWriter = false;
		if (principal != null) {
			if (principal.getUsername().equals(boardDto.getUsername())) {
				isWriter = true;
			}
		}
		model.addAttribute("boardData", boardDto);
		model.addAttribute("isWriter", isWriter);
		return "board/detail";
	}
	
	@GetMapping("/board/modify/{boardId}")
	public String modifyForm(@PathVariable Integer boardId, Model model) {
		model.addAttribute("boardData", boardService.findById(boardId));
		return "board/update_form";
	}

}
