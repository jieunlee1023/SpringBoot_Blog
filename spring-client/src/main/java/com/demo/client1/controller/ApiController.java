package com.demo.client1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.client1.service.RestTemplateService;

@RestController
@RequestMapping("/api/client")
public class ApiController {

	private final RestTemplateService service;

	public ApiController(RestTemplateService service) {
		this.service = service;
	}

	@GetMapping("/get-hello")
	public String getHello() {
		
		return service.반가워().toString();
	}
	
	@GetMapping("/get-hello1")
	public String getHello1() {
		return service.하이().toString();
	}
	
	@GetMapping("/get-hello2")
	public String getHello2() {
		
		return service.안녕().toString();
	}

}
