package edu.curso.java.spring.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

@Entity
@NamedQueries({
@NamedQuery(name="Message.findAll", query = "from Message m"),
@NamedQuery(name="Message.findById", query="from Message m where m.id = :messageId"),
@NamedQuery(name="Message.findBySender", query="from Message m where m.sender = :senderId")
})
public class Message {
	private Long id;
	private String messageText;
	private User sender;
	private Set<User> recipients = new HashSet<User>();
	
	@Id
	@GeneratedValue
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
	
	@JsonBackReference("mensaje-remitente")
	@ManyToOne
	@JoinColumn(name="idRemitenteMensaje")
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	
	@JsonManagedReference("mensaje-destinatarios")
	@OneToMany
	@JoinColumn(name="idMensajeUsuario")
	public Set<User> getRecipients() {
		return recipients;
	}
	public void setRecipients(Set<User> recipients) {
		this.recipients = recipients;
	}
}
