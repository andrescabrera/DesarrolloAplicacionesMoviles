package edu.curso.java.spring.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

@JsonPropertyOrder({"id", "name", "surname", "dateOfBirth"})
@Entity
@NamedQueries({
@NamedQuery(name="User.findAll", query = "from User u"),
@NamedQuery(name="User.findById", query="from User u where u.id = :userId"),
@NamedQuery(name="User.findByName", query="from User u where u.name LIKE :userName AND u.surname LIKE :userSurname")
})
public class User {

	private Long id;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private Set<Post> posts = new HashSet<Post>();
	private Set<User> followers = new HashSet<User>();
	private Set<Rating> ratings = new HashSet<Rating>();
	private Set<Message> messagesSent = new HashSet<Message>();
	private Set<Message> messagesReceived = new HashSet<Message>();
	
	@JsonManagedReference("calificaciones-usuario")
	@OneToMany
	@JoinColumn(name="idUsuarioCalificacion")
	public Set<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	
	@JsonBackReference("usuario-seguidores")
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonManagedReference("publicaciones-usuario")
	@OneToMany
	@JoinColumn(name="idUsuarioCreador")
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
	
	@JsonManagedReference("usuario-seguidores")
	@OneToMany
	@JoinColumn(name="idUsuarioSeguidor")
	public Set<User> getFollowers() {
		return followers;
	}
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	@JsonManagedReference("mensaje-remitente")
	@OneToMany
	@JoinColumn(name="idMensajeUsuario")
	public Set<Message> getMessagesSent() {
		return messagesSent;
	}
	public void setMessagesSent(Set<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}
	
	@JsonManagedReference("mensaje-destinatario")
	@OneToMany
	@JoinColumn(name="idRemitenteMensaje")
	public Set<Message> getMessagesReceived() {
		return messagesReceived;
	}
	public void setMessagesReceived(Set<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}
}
