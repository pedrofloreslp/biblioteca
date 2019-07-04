package com.biblioteca.online.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.online.models.entity.Alumno;
import com.biblioteca.online.models.entity.Copia;
import com.biblioteca.online.models.entity.EstadoPrestamo;
import com.biblioteca.online.models.entity.Libro;
import com.biblioteca.online.models.entity.Morosidad;
import com.biblioteca.online.models.entity.Prestamo;
import com.biblioteca.online.models.entity.TipoLibro;
import com.biblioteca.online.models.entity.Usuario;
import com.biblioteca.online.models.service.ICopiaService;
import com.biblioteca.online.models.service.IMorosidadService;
import com.biblioteca.online.models.service.IPrestamoService;
import com.biblioteca.online.models.service.IUsuarioService;
import com.biblioteca.online.models.service.MorosidadServiceImpl;

@Controller
@RequestMapping("/prestamos")
@SessionAttributes("prestamo")
public class PrestamosController {
	
	@Autowired
	private IPrestamoService prestamoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICopiaService copiaService;
	
	@Autowired
	private IMorosidadService morosidadService;

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name="pagina", defaultValue="0") int page, Model model) {
		List<Prestamo> prestamos = prestamoService.findAll();
		model.addAttribute("prestamos", prestamos);
		return "prestamos/index";
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Prestamo prestamo = new Prestamo();
		List<Usuario> usuarios = usuarioService.findAll();
		model.put("prestamo", prestamo);
		model.put("usuarios", usuarios);
		model.put("titulo", "Crear prestamo");
		model.put("boton", "Crear");
		return "prestamos/formulario";
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.POST)
	public String guardar(Prestamo prestamo, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash, titulo, boton;
		titulo = "Crear prestamo";
		boton = "Crear";
		mensajeFlash = "Prestamo creado con éxito.";
		prestamo.setFechaInicio(new Date());
		prestamo.setEstadoPrestamo(new EstadoPrestamo(1));
		prestamo.setUsuario(new Usuario(prestamo.getUsuario().getId()));
		prestamo.setCopia(new Copia(prestamo.getCopia().getCodigo()));
		Usuario usuario = usuarioService.findById(prestamo.getUsuario().getId());
		Copia copia = copiaService.findById(prestamo.getCopia().getCodigo());
		// Si el usuario es un alumno
		if(usuario.getRolUsuario().getId()==1) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			// Si el libro es una novela.
			if(copia.getLibro().getTipoLibro().getId()==4) {
				calendar.add(Calendar.DAY_OF_YEAR, 5);
			}else{
				calendar.add(Calendar.DAY_OF_YEAR, 3);
			}
			prestamo.setFechaEntrega(calendar.getTime());
		}else {
			prestamo.setFechaEntrega(null);
		}
		prestamoService.save(prestamo);
		copia.setDisponible(false);
		copiaService.save(copia);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/prestamos";
	}
	
	@RequestMapping(value="/renovar/{id}")
	public String renovar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			Prestamo prestamo = prestamoService.findById(id);
			prestamo.setCantidadRenovacion(prestamo.getCantidadRenovacion() + 1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(prestamo.getFechaEntrega());
			calendar.add(Calendar.DAY_OF_YEAR, 3);
			prestamo.setFechaEntrega(calendar.getTime());
			prestamoService.save(prestamo);
			flash.addFlashAttribute("success", "Prestamo renovado con éxito.");
		}
		return "redirect:/prestamos";
	}
	

	@RequestMapping(value="/entregar/{id}")
	public String entregar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			Prestamo prestamo = prestamoService.findById(id);
			prestamo.setEstadoPrestamo(new EstadoPrestamo(2));
			prestamoService.save(prestamo);
			Copia copia = copiaService.findById(prestamo.getCopia().getCodigo());
			copia.setDisponible(true);
			copiaService.save(copia);
			
			//Si el usuario es un alumno.
			if(prestamo.getUsuario().getRolUsuario().getId() == 1) {
				Calendar hoy = Calendar.getInstance();
				hoy.set(Calendar.HOUR, 0);
				hoy.set(Calendar.MINUTE, 0);
				hoy.set(Calendar.SECOND, 0);
				hoy.set(Calendar.MILLISECOND, 0);
				
				Calendar fechaEntrega = Calendar.getInstance();
				fechaEntrega.setTime(prestamo.getFechaEntrega());
				
				if(hoy.after(fechaEntrega)) {
					int dias = (int)((hoy.getTime().getTime() - fechaEntrega.getTime().getTime()) /86400000);
					Morosidad morosidad = new Morosidad();
					morosidad.setFechaInicio(fechaEntrega.getTime());
					morosidad.setDiasRetraso(dias);
					morosidad.setNombreLibro(prestamo.getCopia().getLibro().getNombre());
					morosidad.setAlumno(new Alumno(prestamo.getUsuario().getAlumno().getId()));
					if(dias == 1 || dias == 2) {
						morosidad.setSancion("3 días sin pedir libros.");
						fechaEntrega.add(Calendar.DAY_OF_YEAR, 3);
					}else if(dias == 3 || dias ==4) {
						morosidad.setSancion("5 días sin pedir libros.");
						fechaEntrega.add(Calendar.DAY_OF_YEAR, 5);
					}else if(dias >= 5) {
						fechaEntrega.add(Calendar.DAY_OF_YEAR, 7);
						morosidad.setSancion("7 días de suspensión más citación al apoderado. Si no viene apoderado, no se libera la suspensión.");
						Usuario usuario = usuarioService.findById(prestamo.getUsuario().getId());
						usuario.setSuspendida(true);
						usuarioService.save(usuario);
					}
					morosidad.setFechaFinal(fechaEntrega.getTime());
					morosidadService.save(morosidad);
				}
			}
			flash.addFlashAttribute("success", "Prestamo entregado con éxito.");
		}
		return "redirect:/prestamos";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			prestamoService.deleteById(id);
			flash.addFlashAttribute("success", "Prestamo eliminado con éxito.");
		}
		return "redirect:/prestamos";
	}
}
