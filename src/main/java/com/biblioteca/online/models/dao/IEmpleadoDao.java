package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Empleado;

public interface IEmpleadoDao extends PagingAndSortingRepository<Empleado, Integer>{

	public Empleado findByNombre(String nombre);
	
}
