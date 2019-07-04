package com.biblioteca.online.models.service;

import java.util.List;

import com.biblioteca.online.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();

	public void save(Usuario usuario);
	
	public Usuario findById(int id);
	
	public void deleteById(int id);

}
