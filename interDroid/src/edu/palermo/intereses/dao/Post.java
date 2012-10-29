package edu.palermo.intereses.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="publicaciones")
public class Post {
	//Nombres de las columnas en la BD
	public final static String ID_FIELD_NAME = "idPost";
	public final static String INTEREST_ID_FIELD_NAME = "idInteres";
	public final static String PARSON_ID_FIELD_NAME = "idPersona";
	
	@DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
	private Integer id;
	@DatabaseField(canBeNull = false)
	private String nombre;
	@DatabaseField
	private String descripcion;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = INTEREST_ID_FIELD_NAME)
	private Interes interes;
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = PARSON_ID_FIELD_NAME)
	private Persona persona;
	
	Post() { } // para ormlite

	public Post(String nombre, String descripcion, Interes interes) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.interes = interes;
	}
	
	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@Override
	public String toString() {
		return id + " - " + nombre;
	}
}
