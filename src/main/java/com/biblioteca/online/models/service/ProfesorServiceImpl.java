package com.biblioteca.online.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.IProfesorDao;
import com.biblioteca.online.models.entity.Profesor;

@Service
public class ProfesorServiceImpl implements IProfesorService{
	
	@Autowired
	private IProfesorDao profesorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Profesor> findAll() {
		return (List<Profesor>) profesorDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Profesor findById(int id) {
		return profesorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesorDao.save(profesor);
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
		profesorDao.deleteById(id);
	}
	
}
