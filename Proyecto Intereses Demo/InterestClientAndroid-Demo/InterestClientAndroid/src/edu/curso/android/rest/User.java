package edu.curso.android.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName="usuarios")
public class User {
	@DatabaseField(id=true)
	private Long id;
	@DatabaseField(canBeNull=false)
	private String name;
	@DatabaseField(canBeNull=false)
	private String surname;
	private Date dateOfBirth;
	private Set<Post> posts = new HashSet<Post>();
	private Set<User> followers = new HashSet<User>();
	private Set<Rating> ratings = new HashSet<Rating>();
	private Set<PrivateMessage> messagesSent = new HashSet<PrivateMessage>();
	private Set<PrivateMessage> messagesReceived = new HashSet<PrivateMessage>();
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	public Set<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	public Set<PrivateMessage> getMessagesSent() {
		return messagesSent;
	}
	public void setMessagesSent(Set<PrivateMessage> messagesSent) {
		this.messagesSent = messagesSent;
	}
	public Set<PrivateMessage> getMessagesReceived() {
		return messagesReceived;
	}
	public void setMessagesReceived(Set<PrivateMessage> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}
	
	
	
}
