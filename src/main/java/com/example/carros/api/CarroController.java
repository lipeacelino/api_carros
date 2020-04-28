package com.example.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Carro;
import com.example.carros.service.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	private CarroService service;
	
	@GetMapping
	public Iterable<Carro> getCarros() {
		return service.getCarros();
	}

	@GetMapping("/{id}")
	public Optional<Carro> getCarroById(@PathVariable("id") Long id) {
		return service.getCarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarroByTipo(@PathVariable("tipo") String tipo) {
		return service.getCarroByTipo(tipo);
	}
	
	@PostMapping
	public void addCarro(@RequestBody Carro carro) {
		service.addCarro(carro);
	}
	
	@PutMapping("/{id}")
	public void editCarro(@PathVariable Long id, @RequestBody Carro carro) {
		service.editCarro(id, carro);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCarro(@PathVariable("id") Long id) {
		service.deleteCarro(id);
	}
}
