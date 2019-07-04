package com.biblioteca.online.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.IPrestamoDao;
import com.biblioteca.online.models.entity.Prestamo;

@Service
public class PrestamoServiceImpl implements IPrestamoService{
	
	@Autowired
	private IPrestamoDao prestamoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> findAll() {
		return (List<Prestamo>) prestamoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Prestamo findById(int id) {
		return prestamoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Prestamo prestamo) {
		prestamoDao.save(prestamo);
	}
	
	@Override
	@Transactional
	public void deleteById(int id) {
		prestamoDao.deleteById(id);
	}

}
