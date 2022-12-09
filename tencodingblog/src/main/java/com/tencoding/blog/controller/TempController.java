package com.tencoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
	
	@GetMapping("/temp/home")
	public String tempHome() {
		return "/home.html";
	}
	
	
	@GetMapping("/temp/img")
	public String tempImage() {
		return "/a.png";
	}
	
	@GetMapping("/temp/test")
	public String tempJsp() {
		return "/test";
	} 
	
	@GetMapping("/temp/join")
	public String join() {
		return "/join";
	}
}
