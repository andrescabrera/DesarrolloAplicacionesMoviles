package com.cabrera.intereses.bo;

import java.util.HashSet;
import java.util.Set;

public class Zone {
	private Long id;
	private String name;
	private Zone zone;
	private Set<Post> posts = new HashSet<Post>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
}

