package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Libro;

public interface ILibroDao extends PagingAndSortingRepository<Libro, Integer>{

}
