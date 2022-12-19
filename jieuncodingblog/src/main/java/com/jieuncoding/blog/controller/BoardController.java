package com.jieuncoding.blog.controller;

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
import org.springframework.web.bind.annotation.PutMapping;

import com.jieuncoding.blog.dto.Board;
import com.jieuncoding.blog.dto.ResponseDto;
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
		
		int PAGENATION_BLOCK_COUNT = 3;
		
		int nowPage = boards.getPageable().getPageNumber()+1;
		int startPage = Math.max(nowPage-PAGENATION_BLOCK_COUNT, 1);
		int endPage = Math.min(nowPage+PAGENATION_BLOCK_COUNT,  boards.getTotalPages());
		
		ArrayList<Integer> pageNumbers= new ArrayList<>();
		for (int i = startPage; i <= endPage; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("boards", boards);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		

		return "index";
	}

	@GetMapping("/board/save-form")
	public String saveForm() {
		return "/board/save-form";
	}
	
	@GetMapping("/board/{id}")
	public String showDetail(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.boardDetail(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/{id}/update-form")
	public String updateForm(@PathVariable(name = "id") int boardId, Model model) {
		model.addAttribute("board", boardService.boardDetail(boardId));
		return "/board/update-form";
	}
	
	

}
