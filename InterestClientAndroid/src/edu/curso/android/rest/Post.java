package edu.curso.android.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="publicaciones")
public class Post {
	@DatabaseField(id=true)
	private Long id;
	@DatabaseField(canBeNull=false)
	private String message;
	@DatabaseField
	private Date startDate;
	@DatabaseField
	private Date endDate;
	
	private Localization localization;
	private User user;
	private Interest interest;
	private Set<Rating> ratings = new HashSet<Rating>();
	private Zone zone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Localization getLocalization() {
		return localization;
	}
	public void setLocalization(Localization localization) {
		this.localization = localization;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
	}
	public Set<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
}
