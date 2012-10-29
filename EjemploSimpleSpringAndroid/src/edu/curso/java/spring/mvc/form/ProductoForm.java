package edu.curso.java.spring.mvc.form;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

public class ProductoForm {

	private Long id;
	
	
	@NotEmpty
	@Size(min = 3, max = 50)
	private String nombre;
	
	@NotNull
	@Digits(integer = 8, fraction = 2)
	private Double precio;
	
	private Long idCategoriaProducto;
	
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
    @DateTimeFormat(pattern="dd/MM/yyyy")
	private Date fechaAlta = new Date();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Long getIdCategoriaProducto() {
		return idCategoriaProducto;
	}
	
	public void setIdCategoriaProducto(Long idCategoriaProducto) {
		this.idCategoriaProducto = idCategoriaProducto;
	}
	
	private ArrayList<Long> categoriasIds = new ArrayList<Long>();


	public ArrayList<Long> getCategoriasIds() {
		return categoriasIds;
	}

	public void setCategoriasIds(ArrayList<Long> categoriasIds) {
		this.categoriasIds = categoriasIds;
	}
	
}
