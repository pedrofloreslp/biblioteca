package com.biblioteca.online.controllers;

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

import com.biblioteca.online.models.entity.Copia;
import com.biblioteca.online.models.entity.Libro;
import com.biblioteca.online.models.entity.Profesor;
import com.biblioteca.online.models.entity.RolUsuario;
import com.biblioteca.online.models.entity.TipoLibro;
import com.biblioteca.online.models.entity.Usuario;
import com.biblioteca.online.models.service.ILibroService;
import com.biblioteca.online.models.service.ICopiaService;


@Controller
@RequestMapping("/libros")
@SessionAttributes("libro")
public class LibrosController {
	
	@Autowired
	private ILibroService libroService;
	
	@Autowired
	private ICopiaService copiaService;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name="pagina", defaultValue="0") int page, Model model) {
		List<Libro> libros = libroService.findAll();
		model.addAttribute("libros", libros);
		return "libros/index";
	}

	@RequestMapping(value = "/formulario", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		Libro libro = new Libro();
		model.put("libro", libro);
		model.put("titulo", "Crear libro");
		model.put("boton", "Crear");
		return "libros/formulario";
	}
	
	@RequestMapping(value="/formulario/{id}", method = RequestMethod.GET)
	public String modificar(@PathVariable(value="id") int id, Map<String, Object> model, RedirectAttributes flash) {
		Libro libro = null;
		if(id > 0) {
			libro = libroService.findById(id);
			if(libro == null) {
				flash.addFlashAttribute("danger", "El libro no existe.");
				return "redirect:/libros";
			}
		} else {
			flash.addFlashAttribute("danger", "El ID del libro no puede ser cero.");
			return "redirect:/libros";
		}
		model.put("libro", libro);
		model.put("titulo", "Modificar libro");
		model.put("boton", "Modificar");
		return "libros/formulario";
	}
	
	@RequestMapping(value = "/formulario", method = RequestMethod.POST)
	public String guardar(Libro libro, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		String mensajeFlash, titulo, boton;
		if(libro.getId() == 0) {
			titulo = "Crear libro";
			boton = "Crear";
			mensajeFlash = "Libro creado con éxito.";
		} else {
			titulo = "Modificar libro";
			boton = "Modificar";
			mensajeFlash = "Libro modificado con éxito";
			libro.setTipoLibro(new TipoLibro(libro.getTipoLibro().getId()));
		}
		libroService.save(libro);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/libros";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			libroService.deleteById(id);
			flash.addFlashAttribute("success", "Libro eliminado con éxito.");
		}
		return "redirect:/libros";
	}
	
	@RequestMapping(value="/copias/{id}", method = RequestMethod.GET)
	public String listarCopias(@PathVariable(value="id") int id, Model model) {
		Libro libro = libroService.findById(id);
		model.addAttribute("libro", libro);
		model.addAttribute("copias", libro.getCopias());
		return "libros/copias";
	}
	
	@RequestMapping(value="/copias/crear/{id}")
	public String crearCopia(@PathVariable(value="id") int id, RedirectAttributes flash) {
		if(id > 0) {
			Copia copia = new Copia();
			copia.setLibro(new Libro(id));
			copia.setDisponible(true);
			copiaService.save(copia);
			flash.addFlashAttribute("success", "Copia generada con éxito.");
		}
		return "redirect:/libros/copias/"+id;
	}
	
	@RequestMapping(value="/copias/eliminar/{id}")
	public String eliminarCopia(@PathVariable(value="id") int id, RedirectAttributes flash) {
		Copia copia = copiaService.findById(id);
		copiaService.deleteById(id);
		flash.addFlashAttribute("success", "Copia eliminada con éxito.");
		return "redirect:/libros/copias/"+copia.getLibro().getId();
	}
}
