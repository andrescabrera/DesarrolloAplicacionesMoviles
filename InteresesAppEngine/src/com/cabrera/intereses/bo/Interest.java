package com.cabrera.intereses.bo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Interest {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	private String description;
	private Interest interesPadre;
	private Set<Interest> interesesHijos = new HashSet<Interest>();
	private Set<Post> posts = new HashSet<Post>();
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Interest getInteresPadre() {
		return interesPadre;
	}
	public void setInteresPadre(Interest interesPadre) {
		this.interesPadre = interesPadre;
	}
	public Set<Interest> getInteresesHijos() {
		return interesesHijos;
	}
	public void setInteresesHijos(Set<Interest> interesesHijos) {
		this.interesesHijos = interesesHijos;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}