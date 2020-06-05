package com.example.carros.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.service.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	// recebe o id de um carro e retorna a url do carro em questão
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Autowired
	private CarroService service;

	@GetMapping
	public ResponseEntity getCarros() {
		List<Carro> carros = service.getCarros();

		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<CarroDTO> carrosDTO = carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
			return ResponseEntity.ok(carrosDTO);
//		List<CarroDTO> carrosDTO = new ArrayList<>();
//		for (Carro c: carros) {
//			carrosDTO.add(new CarroDTO(c));
//		}
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getCarroById(@PathVariable("id") Long id) {
		Optional<CarroDTO> carroDTO = service.getCarroById(id);

		//if (carroDTO.isPresent()) {
			return ResponseEntity.ok(carroDTO);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo) {
		List<Carro> carros = service.getCarroByTipo(tipo);

		if (carros.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList()));
		}
	}

	@PostMapping
	public ResponseEntity addCarro(@RequestBody Carro carro) {
		service.addCarro(carro);
		URI location = this.getUri(carro.getId());
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity editCarro(@PathVariable Long id, @RequestBody Carro carro) {
		Carro c = service.editCarro(id, carro);
		/*
		if (c == null) {
			return ResponseEntity.notFound().build();
		}*/
		return ResponseEntity.ok(c);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteCarro(@PathVariable("id") Long id) {
		service.deleteCarro(id);
		return ResponseEntity.ok().build();
	}
}
