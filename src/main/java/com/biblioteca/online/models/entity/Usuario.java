package com.biblioteca.online.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre_completo")
	private String nombreCompleto;
	
	private int rut;
	
	private char verificador;
	
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaNacimiento;
	
	private String direccion;
	
	private String comuna;
	
	@Column(name="telefono_fijo")
	private int telefonoFijo;
	
	@Column(name="telefono_celular")
	private int telefonoCelular;
	
	private boolean suspendida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_rol_usuario")
	private RolUsuario rolUsuario;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Alumno alumno;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
	private Profesor profesor;
	
	public Usuario() {
		super();
	}
	
	public Usuario(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public int getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(int telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public int getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(int telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public boolean isSuspendida() {
		return suspendida;
	}

	public void setSuspendida(boolean suspendida) {
		this.suspendida = suspendida;
	}

	public RolUsuario getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(RolUsuario rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
}
