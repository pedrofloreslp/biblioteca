package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Profesor;

public interface IProfesorDao extends PagingAndSortingRepository<Profesor, Integer>{

}
