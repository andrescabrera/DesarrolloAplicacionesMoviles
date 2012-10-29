package edu.curso.android.rest;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.*;

@DatabaseTable(tableName="privateMessages")
public class PrivateMessage {
	
	@DatabaseField(id=true)
	private Long id;
	@DatabaseField(canBeNull=false)
	private String messageText;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Set<User> getRecipients() {
		return recipients;
	}
	public void setRecipients(Set<User> recipients) {
		this.recipients = recipients;
	}
	private User sender;
	private Set<User> recipients = new HashSet<User>();
}
