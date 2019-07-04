package com.biblioteca.online.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="libro")
public class Libro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_libro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	
	private String autor;
	
	private String editorial;
	
	private int numeroPaginas;
	
	private String ubicacion;
	
	private String categoria;
	
	@Column(name="numero_copias")
	private int numeroCopias;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tipo_libro")
	private TipoLibro tipoLibro;
	
	@OneToMany(fetch= FetchType.LAZY)
	@JoinColumn(name="id_libro")
	private List<Copia> copias;
	
	public Libro() {
		copias = new ArrayList<Copia>();
	}
	
	public Libro(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getNumeroCopias() {
		return numeroCopias;
	}

	public void setNumeroCopias(int numeroCopias) {
		this.numeroCopias = numeroCopias;
	}

	public TipoLibro getTipoLibro() {
		return tipoLibro;
	}

	public void setTipoLibro(TipoLibro tipoLibro) {
		this.tipoLibro = tipoLibro;
	}

	public List<Copia> getCopias() {
		return copias;
	}

	public void setCopias(List<Copia> copias) {
		this.copias = copias;
	}
	
	public int getCopiasCantidad() {
		return this.copias.size();
	}
	
	public int getCopiasCantidadDisponible() {
		int copiasCantidadDisponible = 0;
		for (Copia copia : this.copias) {
			if(copia.isDisponible()) {
				copiasCantidadDisponible++;
			}
		}
		return copiasCantidadDisponible;
	}

}
