package com.biblioteca.online.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.ICopiaDao;
import com.biblioteca.online.models.entity.Copia;

@Service
public class CopiaServiceImpl implements ICopiaService{
	
	@Autowired
	private ICopiaDao copiaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Copia> findAll() {
		return (List<Copia>) copiaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Copia findById(int id) {
		return copiaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Copia copia) {
		copiaDao.save(copia);
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
		copiaDao.deleteById(id);
	}


}
