package com.biblioteca.online.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.IUsuarioDao;
import com.biblioteca.online.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		usuarioDao.deleteById(id);
	}
	
	
	
}
