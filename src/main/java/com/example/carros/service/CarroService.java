package com.example.carros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroRepository;
import com.example.carros.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	public List<Carro> getCarros() {
		return repository.findAll();
	}

	public Optional<CarroDTO> getCarroById(Long id) {
		CarroDTO.create(repository.findById(id).get());
		return repository.findById(id).map(c -> CarroDTO.create(c));
	}

	public void deleteCarro(Long id) {
			repository.deleteById(id);
	}

	public List<Carro> getCarroByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public void addCarro(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possível inserir o registro.");
		repository.save(carro);
	}

	// futuramente é bom trocar esse método por uma lambda + map
	public Carro editCarro(Long id, Carro carro) {

		Assert.notNull(id, "Não foi possível atualizar o registro.");

		// busca de carro no banco de dados
		Optional<Carro> carroOptional = repository.findById(id);

		//if (carroOptional.isPresent()) {
			Carro carroBd = carroOptional.get();

			// copiando as propriedades para o carro do bd
			carroBd.setNome(carro.getNome());
			carroBd.setTipo(carro.getTipo());

			// salvando carro atualizado no bd
			return repository.save(carroBd);
		}/* else {
			return null;
		}*/

	}
//}
