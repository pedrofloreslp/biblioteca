package com.biblioteca.online.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.online.models.dao.IEmpleadoDao;
import com.biblioteca.online.models.entity.Empleado;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException{
		Empleado empleado = empleadoDao.findByNombre(nombre);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_"+empleado.getRolEmpleado().getNombre()));		
//		System.out.println("---"+empleado.getRolEmpleado().getNombre());
		return new User(empleado.getNombre(), empleado.getContrasena(), true, true, true, true, roles);
	}
	
}
