package com.example.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Iterable<Carro>> getCarros() {
		return ResponseEntity.ok(service.getCarros());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Carro>> getCarroById(@PathVariable("id") Long id) {
		Optional<Carro> carro = service.getCarroById(id);
		
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro);
		} 
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Carro>> getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<Carro> carros = service.getCarroByTipo(tipo);
		
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros);
		}
	}
	
	@PostMapping
	public ResponseEntity<Carro> addCarro(@RequestBody Carro carro) {
		
		 //o ideal seria colocar "not null" nos atributos no bd 
		 if (carro.getNome().isBlank() || carro.getTipo().isBlank()) {
			 return ResponseEntity.badRequest().build();
		 }
		 else {
			 service.addCarro(carro);
			 return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/carros/" + carro.getId())).build();
		 }
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
