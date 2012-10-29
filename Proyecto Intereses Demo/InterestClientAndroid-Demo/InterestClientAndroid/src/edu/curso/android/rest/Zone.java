package edu.curso.android.rest;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="zonas")
public class Zone {
	@DatabaseField(id=true)
	private Long id;
	@DatabaseField(canBeNull=false)
	private String name;
	public Set<Zone> getZones() {
		return zones;
	}
	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	private Set<Zone> zones = new HashSet<Zone>();
	private Set<Post> posts = new HashSet<Post>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
