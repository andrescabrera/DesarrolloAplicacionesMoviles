package edu.curso.android.rest;

import com.j256.ormlite.field.*;
import com.j256.ormlite.table.DatabaseTable;



@DatabaseTable(tableName="categoria_producto")
public class CategoriaProducto {
	
	@DatabaseField(id = true)
	private Long id;
	
	@DatabaseField(canBeNull = false)
	private String nombre;
	

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
	
	@Override
	public String toString() {
		return nombre;
	}
}
