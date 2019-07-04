package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Profesor;

public interface IProfesorService {
	
	public List<Profesor> findAll();

	public void save(Profesor profesor);
	
	public Profesor findById(int id);
	
	public void deleteById(int id);

}
