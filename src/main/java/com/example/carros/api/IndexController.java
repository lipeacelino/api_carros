package com.example.carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String home() {
		return "API dos carros";
	}
	/*
	@GetMapping("/teste")
	public String teste() {
		return "Teste Spring Boot";
	}
	
	@PostMapping("/post")
	public String post(@RequestParam("id") String id) {
		return "Spring Boot POST!" + id;
	}
	*/
}
