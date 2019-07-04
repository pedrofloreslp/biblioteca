package com.biblioteca.online.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.biblioteca.online.models.entity.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Integer>{

}
