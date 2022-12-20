package com.jecoding.blog.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.jecoding.blog.dto.Board;
import com.jecoding.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/", "/board/search" })
	public String index(@RequestParam(required = false) String q, Model model,
			@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {

		String searchTitle = q == null ? "" : q;
		Page<Board> boards = boardService.searchBoard(
				searchTitle, pageable);
		
		int PAGENATION_BLOCK_COUNT = 3;
		
		int nowPage  = boards.getPageable().getPageNumber()+1;
		int startPage = Math.max(nowPage-PAGENATION_BLOCK_COUNT, 1);
		int endPage = Math.min(nowPage+PAGENATION_BLOCK_COUNT, boards.getTotalPages());
		
		ArrayList<Integer> pageNumbers = new ArrayList<>();
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("boards", boards);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("q", searchTitle);
		
		
		return "index";
	}

	@GetMapping("/board/save-form")
	public String saveForm() {
		return "/board/save-form";
	}
	
	@GetMapping("/board/{boardId}")
	public String showDetail(@PathVariable int boardId, Model model) {
		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/detail";
	}
	
	@GetMapping("/board/{boardId}/update-form")
	public String updateForm(@PathVariable int boardId, Model model) {
		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/update_form";
	}
	
	//board/${board.id}/reply/${reply.id}/update-reply-form
	@GetMapping("/board/{boardId}/reply/{replyId}/update-reply-form")
	public String updateReplyForm(@PathVariable int boardId, @PathVariable int replyId,
			Model model) {
		model.addAttribute("board", boardService.boardDetail(boardId));
		model.addAttribute("replyData", boardService.replyDetail(replyId));
		return "/board/update-reply-form";
	}

	

}
