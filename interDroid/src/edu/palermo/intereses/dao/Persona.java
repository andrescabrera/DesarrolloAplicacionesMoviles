package edu.palermo.intereses.dao;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="personas")
public class Persona {
	
	public static final String NAME_FIELD_NAME = "nombrePersona";
	public static final String DATE_FIELD_NAME = "fechaNacimientoPersona";
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField(canBeNull = false, columnName=NAME_FIELD_NAME)
	private String nombre;
	
	@DatabaseField(columnName=DATE_FIELD_NAME)
	private Date fechaNacimiento;
	
	@ForeignCollectionField
	private ForeignCollection<Post> publicaciones;
	
	@ForeignCollectionField
	private ForeignCollection<Suscripcion> suscripciones;
	
	Persona () {}
	
	public Persona (String nombre, Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public ForeignCollection<Post> getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(ForeignCollection<Post> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public ForeignCollection<Suscripcion> getSuscripciones() {
		return suscripciones;
	}

	public void setSuscripciones(ForeignCollection<Suscripcion> suscripciones) {
		this.suscripciones = suscripciones;
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		return nombre.equals(((Persona) other).nombre);
	}
	
	@Override
	public String toString() {
		return id + " - " + nombre;
	}
}