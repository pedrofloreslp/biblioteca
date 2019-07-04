package com.biblioteca.online.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.IMorosidadDao;
import com.biblioteca.online.models.entity.Morosidad;

@Service
public class MorosidadServiceImpl implements IMorosidadService{

	@Autowired
	private IMorosidadDao morosidadDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Morosidad> findAll() {
		return (List<Morosidad>) morosidadDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Morosidad findById(int id) {
		return morosidadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Morosidad alumno) {
		morosidadDao.save(alumno);
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
		morosidadDao.deleteById(id);
	}
}
