package edu.palermo.intereses.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="suscripciones")
public class Suscripcion {
	
	private final static String SUSCRIPTOR_ID_FIELD_NAME = "idSuscriptor";
	private final static String PUBLICACION_ID_FIELD_NAME = "idPost";
	
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField
	private String comentario;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, columnName=SUSCRIPTOR_ID_FIELD_NAME)
	private Persona suscriptor;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, columnName=PUBLICACION_ID_FIELD_NAME)
	private Post post;
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Persona getSuscriptor() {
		return suscriptor;
	}
	public void setSuscriptor(Persona suscriptor) {
		this.suscriptor = suscriptor;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	} 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return id + " - " + comentario;
	}
}
