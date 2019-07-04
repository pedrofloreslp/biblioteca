package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Alumno;


public interface IAlumnoService {

	public List<Alumno> findAll();

	public void save(Alumno alumno);
	
	public Alumno findById(int id);
	
	public void deleteById(int id);
	
}
