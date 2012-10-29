package edu.palermo.intereses.dao;

import java.io.Serializable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "intereses")
public class Interes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -504126237248807586L;

	public final static String NAME_FIELD_NAME="nombreInteres";
	
	@DatabaseField(generatedId=true)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@DatabaseField(canBeNull=false, columnName=NAME_FIELD_NAME)
	private String nombre;
	@ForeignCollectionField
	private ForeignCollection<Post> publicaciones;
	
	public Interes () {}
	
	public Interes (String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return nombre.equals(((Interes) other).nombre);
	}

	public ForeignCollection<Post> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(ForeignCollection<Post> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
	@Override
	public String toString() {
		return id + " - " + nombre;
	}
}
