package com.example.carros.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionConfig {
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class, // Lançado pelo deleteCarro()
		java.util.NoSuchElementException.class //Lançado pelo editCarro(), getCarroById()
	})
	public ResponseEntity errorNotFound(Exception e) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({
		java.lang.IllegalArgumentException.class //Lançado pelo addCarro()
	})
	public ResponseEntity errorIllegalArgument(Exception e) {
		return ResponseEntity.badRequest().build();
	}

	
}
