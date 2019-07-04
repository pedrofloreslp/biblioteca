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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="alumno")
public class Alumno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_alumno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String curso;
	
	private String parentesco;
	
	@Column(name="nombre_completo")
	private String nombreCompleto;
	
	private int rut;
	
	private char verificador;
	
	private String direccion;
	
	private String comuna;
	
	@Column(name="telefono_fijo")
	private String telefonoFijo;
	
	@Column(name="telefono_celular")
	private String telefonoCelular;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_alumno")
	private List<Morosidad> morosidades;

	public Alumno() {
		super();
		morosidades = new ArrayList<Morosidad>();
	}
	
	public Alumno(int id) {
		super();
		this.id = id;
		morosidades = new ArrayList<Morosidad>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public char getVerificador() {
		return verificador;
	}

	public void setVerificador(char verificador) {
		this.verificador = verificador;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Morosidad> getMorosidades() {
		return morosidades;
	}

	public void setMorosidades(List<Morosidad> morosidades) {
		this.morosidades = morosidades;
	}
}
