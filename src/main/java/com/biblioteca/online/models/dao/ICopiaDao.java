package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Copia;

public interface ICopiaDao extends PagingAndSortingRepository<Copia, Integer>{

}
