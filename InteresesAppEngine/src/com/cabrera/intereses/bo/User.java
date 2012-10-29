package com.cabrera.intereses.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable
public class User {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
    @Persistent
	private String name;
    @Persistent
	private String surname;
    @Persistent
	private Date dateOfBirth;
    @Persistent(mappedBy="user")
	private Set<Post> posts = new HashSet<Post>();
    @Persistent
	private Set<Key> followers = new HashSet<Key>();
    @Persistent(mappedBy="post")
	private Set<Rating> ratings = new HashSet<Rating>();
    @Persistent
	private Set<Message> messagesSent = new HashSet<Message>();
    @Persistent
	private Set<Message> messagesReceived = new HashSet<Message>();
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
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
	public Set<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	public Set<Message> getMessagesSent() {
		return messagesSent;
	}
	public void setMessagesSent(Set<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}
	public Set<Message> getMessagesReceived() {
		return messagesReceived;
	}
	public void setMessagesReceived(Set<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}
	public Set<Key> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<Key> followers) {
		this.followers = followers;
	}
}