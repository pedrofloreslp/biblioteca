package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Alumno;

public interface IAlumnoDao extends PagingAndSortingRepository<Alumno, Integer>{

}
