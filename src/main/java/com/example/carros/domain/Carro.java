package com.example.carros.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity(name = "carro")
public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tipo")
	private String tipo;
	
	public Carro() {

	}
	
	public Carro(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
}
