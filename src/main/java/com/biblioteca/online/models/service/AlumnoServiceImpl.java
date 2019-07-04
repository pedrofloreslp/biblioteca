package com.biblioteca.online.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.IAlumnoDao;
import com.biblioteca.online.models.entity.Alumno;

@Service
public class AlumnoServiceImpl implements IAlumnoService{

	@Autowired
	private IAlumnoDao alumnoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAll() {
		return (List<Alumno>) alumnoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Alumno findById(int id) {
		return alumnoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Alumno alumno) {
		alumnoDao.save(alumno);
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
		alumnoDao.deleteById(id);
	}
	
}
