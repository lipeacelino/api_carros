package com.example.carros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Carro;
import com.example.carros.service.CarroService;

@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	private CarroService service;
	
	@GetMapping
	public String home() {
		return "API dos carros";
	}
	
	@GetMapping("/api/v1/carros")
	public List<Carro> teste() {
		return service.getCarros();
	}
	
}
