package com.example.carros.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;
	
	public Iterable<Carro> getCarros() {
		return repository.findAll();
	}

	public Optional<Carro> getCarroById(Long id) {
		return repository.findById(id);
	}

	public void deleteCarro(Long id) {
		repository.deleteById(id);
	}

	public Iterable<Carro> getCarroByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public void addCarro(Carro carro) {
		repository.save(carro);
	}

	// futuramente é bom trocar esse método por uma lambda + map
	public void editCarro(Long id, Carro carro) {
		
		//busca de carro no banco de dados 
		Optional<Carro> carroOptional = repository.findById(id);
	
		if (carroOptional.isPresent()) {
			Carro carroBd = carroOptional.get();
			
			//copiando as propriedades para o carro do bd
			carroBd.setNome(carro.getNome());
			carroBd.setTipo(carro.getTipo());
		
			//salvando carro atualizado no bd
			repository.save(carroBd);
		}
		
	}
}
