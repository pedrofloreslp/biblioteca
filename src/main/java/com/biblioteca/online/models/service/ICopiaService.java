package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Copia;

public interface ICopiaService {
	
	public List<Copia> findAll();

	public void save(Copia copia);
	
	public Copia findById(int id);
	
	public void deleteById(int id);

}
