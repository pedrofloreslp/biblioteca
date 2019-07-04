package com.biblioteca.online.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.online.models.entity.Alumno;
import com.biblioteca.online.models.entity.Profesor;
import com.biblioteca.online.models.entity.RolUsuario;
import com.biblioteca.online.models.entity.Usuario;
import com.biblioteca.online.models.service.IAlumnoService;
import com.biblioteca.online.models.service.IProfesorService;
import com.biblioteca.online.models.service.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuariosController {
	
	@Autowired
	private IAlumnoService alumnoService;
	@Autowired
	private IProfesorService profesorService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/alumnos", method = RequestMethod.GET)
	public String listarAlumnos(Model model) {
		List<Alumno> alumnos = alumnoService.findAll();
		model.addAttribute("alumnos", alumnos);
		return "usuarios/alumnos";
	}
	
	@RequestMapping(value = "/profesores", method = RequestMethod.GET)
	public String listarProfesores(Model model) {
		List<Profesor> profesores = profesorService.findAll();
		model.addAttribute("profesores", profesores);
		return "usuarios/profesores";
	}

	@RequestMapping(value = "/alumnos/formulario", method = RequestMethod.GET)
	public String crearAlumno(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Crear usuario");
		model.put("boton", "Crear");
		return "usuarios/f-alumno";
	}
	
	@RequestMapping(value = "/profesores/formulario", method = RequestMethod.GET)
	public String crearProfesor(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "Crear usuario");
		model.put("boton", "Crear");
		return "usuarios/f-profesor";
	}
	
	@RequestMapping(value="/alumnos/formulario/{id}", method = RequestMethod.GET)
	public String modificarAlumno(@PathVariable(value="id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Usuario usuario = null;
		if(id > 0) {
			usuario = usuarioService.findById(alumnoService.findById(id).getUsuario().getId());
			if(usuario == null) {
				flash.addFlashAttribute("danger", "El alumno no existe.");
				return "redirect:/usuarios/alumnos";
			}
		} else {
			flash.addFlashAttribute("danger", "El ID del alumno no puede ser cero.");
			return "redirect:/usuarios/alumnos";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Modificar alumno");
		model.put("boton", "Modificar");
		return "usuarios/f-alumno";
	}
	
	@RequestMapping(value="/profesores/formulario/{id}", method = RequestMethod.GET)
	public String modificarProfesor(@PathVariable(value="id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Usuario usuario = null;
		if(id > 0) {
			usuario = usuarioService.findById(profesorService.findById(id).getUsuario().getId());
			if(usuario == null) {
				flash.addFlashAttribute("danger", "El profesor no existe.");
				return "redirect:/usuarios/profesores";
			}
		} else {
			flash.addFlashAttribute("danger", "El ID del profesor no puede ser cero.");
			return "redirect:/usuarios/profesores";
		}
		model.put("usuario", usuario);
		model.put("titulo", "Modificar profesor");
		model.put("boton", "Modificar");
		return "usuarios/f-profesor";
	}
	
	@RequestMapping(value = "/alumnos/formulario", method = RequestMethod.POST)
	public String guardarAlumno(Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash, titulo, boton;
		if(usuario.getId() == 0) {
			titulo = "Crear alumno";
			boton = "Crear";
			mensajeFlash = "Alumno creado con éxito.";
			Usuario usuario_ = new Usuario();
			usuario_.setNombreCompleto(usuario.getNombreCompleto());
			usuario_.setRut(usuario.getRut());
			usuario_.setVerificador(usuario.getVerificador());
			usuario_.setFechaNacimiento(usuario.getFechaNacimiento());
			usuario_.setDireccion(usuario.getDireccion());
			usuario_.setComuna(usuario.getComuna());
			usuario_.setTelefonoFijo(usuario.getTelefonoFijo());
			usuario_.setTelefonoCelular(usuario.getTelefonoCelular());
			usuario_.setRolUsuario(new RolUsuario(1));
			usuarioService.save(usuario_);
			Alumno alumno = new Alumno();
			alumno.setUsuario(usuario_);
			alumno.setCurso(usuario.getAlumno().getCurso());
			alumno.setParentesco(usuario.getAlumno().getParentesco());
			alumno.setNombreCompleto(usuario.getAlumno().getNombreCompleto());
			alumno.setRut(usuario.getAlumno().getRut());
			alumno.setVerificador(usuario.getAlumno().getVerificador());
			alumno.setDireccion(usuario.getAlumno().getDireccion());
			alumno.setComuna(usuario.getAlumno().getComuna());
			alumno.setTelefonoFijo(usuario.getAlumno().getTelefonoFijo());
			alumno.setTelefonoCelular(usuario.getAlumno().getTelefonoCelular());
			alumnoService.save(alumno);
		} else {
			titulo = "Modificar alumno";
			boton = "Modificar";
			mensajeFlash = "Alumno modificado con éxito";
			usuarioService.save(usuario);
			Alumno alumno = alumnoService.findById(usuario.getAlumno().getId());
			alumno.setCurso(usuario.getAlumno().getCurso());
			alumno.setParentesco(usuario.getAlumno().getParentesco());
			alumno.setNombreCompleto(usuario.getAlumno().getNombreCompleto());
			alumno.setRut(usuario.getAlumno().getRut());
			alumno.setVerificador(usuario.getAlumno().getVerificador());
			alumno.setDireccion(usuario.getAlumno().getDireccion());
			alumno.setComuna(usuario.getAlumno().getComuna());
			alumno.setTelefonoFijo(usuario.getAlumno().getTelefonoFijo());
			alumno.setTelefonoCelular(usuario.getAlumno().getTelefonoCelular());
			alumnoService.save(alumno);
		}
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/usuarios/alumnos";
	}
	
	@RequestMapping(value = "/profesores/formulario", method = RequestMethod.POST)
	public String guardarProfesor(Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash, titulo, boton;
		if(usuario.getId() == 0) {
			titulo = "Crear profesor";
			boton = "Crear";
			mensajeFlash = "Profesor creado con éxito.";
			Usuario usuario_ = new Usuario();
			usuario_.setNombreCompleto(usuario.getNombreCompleto());
			usuario_.setRut(usuario.getRut());
			usuario_.setVerificador(usuario.getVerificador());
			usuario_.setFechaNacimiento(usuario.getFechaNacimiento());
			usuario_.setDireccion(usuario.getDireccion());
			usuario_.setComuna(usuario.getComuna());
			usuario_.setTelefonoFijo(usuario.getTelefonoFijo());
			usuario_.setTelefonoCelular(usuario.getTelefonoCelular());
			usuario_.setRolUsuario(new RolUsuario(2));
			usuarioService.save(usuario_);
			Profesor profesor = new Profesor();
			profesor.setUsuario(usuario_);
			profesor.setCargo(usuario.getProfesor().getCargo());
			profesorService.save(profesor);
		} else {
			titulo = "Modificar profesor";
			boton = "Modificar";
			mensajeFlash = "Profesor modificado con éxito";
			usuarioService.save(usuario);
			Profesor profesor = profesorService.findById(usuario.getProfesor().getId());
			profesor.setCargo(usuario.getProfesor().getCargo());
			profesorService.save(profesor);
		}
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/usuarios/profesores";
	}
	
	@RequestMapping(value="/alumnos/eliminar/{id}")
	public String eliminarAlumno(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			Alumno alumno = alumnoService.findById(id);
			alumnoService.deleteById(id);
			usuarioService.deleteById(alumno.getUsuario().getId());
			flash.addFlashAttribute("success", "Alumno eliminado con éxito.");
		}
		return "redirect:/usuarios/alumnos";
	}
	
	@RequestMapping(value="/profesores/eliminar/{id}")
	public String eliminarProfesor(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			Profesor profesor = profesorService.findById(id);
			profesorService.deleteById(id);
			usuarioService.deleteById(profesor.getUsuario().getId());
			flash.addFlashAttribute("success", "Profesor eliminado con éxito.");
		}
		return "redirect:/usuarios/profesores";
	}
	
	@RequestMapping(value="/alumnos/morosidad/{id}")
	public String listarMorosidad(@PathVariable(value="id") int id, Model model) {
		Alumno alumno = alumnoService.findById(id);
		model.addAttribute("nombreAlumno", alumno.getUsuario().getNombreCompleto());
		model.addAttribute("morosidades", alumno.getMorosidades());
		return "usuarios/morosidad";
	}
}
