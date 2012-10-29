package edu.curso.android.rest;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="intereses")
public class Interest {
	
	@DatabaseField(id = true)
	private Long id;
	@DatabaseField(canBeNull = false)
	private String description;
	private Interest interesPadre;
	private Set<Interest> interesesHijos = new HashSet<Interest>();
	private Set<Post> posts = new HashSet<Post>();
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Interest> getInterests() {
		return interesesHijos;
	}
	public void setInterests(Set<Interest> interests) {
		this.interesesHijos = interests;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return description;
	}
	public Interest getInteresPadre() {
		return interesPadre;
	}
	public void setInteresPadre(Interest interesPadre) {
		this.interesPadre = interesPadre;
	}
}
