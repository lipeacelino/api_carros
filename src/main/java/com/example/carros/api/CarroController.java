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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.carros.domain.Carro;
import com.example.carros.service.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri();
	}
	
	@Autowired
	private CarroService service;
	
	@GetMapping
	public ResponseEntity getCarros() {
		return ResponseEntity.ok(service.getCarros());
	}

	@GetMapping("/{id}")
	public ResponseEntity getCarroById(@PathVariable("id") Long id) {
		Optional<Carro> carro = service.getCarroById(id);
		
		if(carro.isPresent()) {
			return ResponseEntity.ok(carro);
		} 
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<Carro> carros = service.getCarroByTipo(tipo);
		
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros);
		}
	}
	
	@PostMapping
	public ResponseEntity addCarro(@RequestBody Carro carro) {
		
		try {
			service.addCarro(carro);
			URI location = this.getUri(carro.getId());
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity editCarro(@PathVariable Long id, @RequestBody Carro carro) {
		Carro c = service.editCarro(id, carro);
		if (c == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCarro(@PathVariable("id") Long id) {
		boolean carroDeletado = service.deleteCarro(id);
		if (carroDeletado) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
