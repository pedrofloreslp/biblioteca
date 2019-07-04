package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Prestamo;

public interface IPrestamoDao extends PagingAndSortingRepository<Prestamo, Integer>{

}
