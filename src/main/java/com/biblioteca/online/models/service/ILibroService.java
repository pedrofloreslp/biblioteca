package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();

	public void save(Libro libro);
	
	public Libro findById(int id);
	
	public void deleteById(int id);

}
