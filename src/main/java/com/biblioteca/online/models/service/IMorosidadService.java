package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Morosidad;

public interface IMorosidadService {
	
	public List<Morosidad> findAll();

	public void save(Morosidad morosidad);
	
	public Morosidad findById(int id);
	
	public void deleteById(int id);

}
