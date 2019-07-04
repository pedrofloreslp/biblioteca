package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Prestamo;

public interface IPrestamoService {
	
	public List<Prestamo> findAll();

	public void save(Prestamo prestamo);
	
	public Prestamo findById(int id);
	
	public void deleteById(int id);

}
