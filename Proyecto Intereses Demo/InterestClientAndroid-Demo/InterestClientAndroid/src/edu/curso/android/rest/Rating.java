package edu.curso.android.rest;

import com.j256.ormlite.table.*;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName="calificaciones")
public class Rating {
	
	@DatabaseField(id=true)
	private Long id;
	@DatabaseField(canBeNull=false)
	private int ratingPoints;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRatingPoints() {
		return ratingPoints;
	}
	public void setRatingPoints(int ratingPoints) {
		this.ratingPoints = ratingPoints;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private Post post;
	private User user;
}
