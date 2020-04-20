package com.example.carros.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.carros.domain.Carro;

@Service
public class CarroService {
	
	public List<Carro> getCarros() {
		
		List<Carro> carros = new ArrayList<Carro>();
		
		carros.add(new Carro(1L, "Chevette"));
		carros.add(new Carro(1L, "Gol"));
		carros.add(new Carro(1L, "Fusca"));
		
		return carros;
	}

}
