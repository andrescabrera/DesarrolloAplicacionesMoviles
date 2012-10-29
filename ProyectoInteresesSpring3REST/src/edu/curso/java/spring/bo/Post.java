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

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

@JsonPropertyOrder({"id","message","startDate","endDate","localization"})
@Entity
@NamedQueries({
@NamedQuery(name="Post.findAll", query = "from Post p"),
@NamedQuery(name="Post.findById", query="from Post p where p.id = :postId"),
@NamedQuery(name="Post.findByText", query="from Post p where p.message LIKE :postText"),
@NamedQuery(name="Post.findByInterest", query="from Post p where p.interest = :interestId")
})
public class Post {
	private Long id;
	private String message;
	private Date startDate;
	private Date endDate;
	private Localization localization;
	private User user;
	private Interest interest;
	private Set<Rating> ratings = new HashSet<Rating>();
	private Zone zone;

	@Id
	@GeneratedValue
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
	
	@JsonBackReference("zona-publicacion")
	@ManyToOne
	@JoinColumn(name="idLocalizacionPublicacion")
	public Localization getLocalization() {
		return localization;
	}
	public void setLocalization(Localization localization) {
		this.localization = localization;
	}
	
	@JsonBackReference("intereses-publicacion")
	@ManyToOne
	@JoinColumn(name="idInteresPublicacion")
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
	}
	
	@JsonManagedReference("calificaciones-post")
	@OneToMany
	@JoinColumn(name="idPostCalificado")
	public Set<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	
	@JsonBackReference("publicaciones-usuario")
	@ManyToOne
	@JoinColumn(name="idUsuarioCreador")
	public User getUsuario() {
		return user;
	}

	public void setUsuario(User usuario) {
		this.user = usuario;
	}
	
	@JsonBackReference("zona-publicacion")
	@ManyToOne
	@JoinColumn(name="idZonaPublicacion")
	public Zone getZona() {
		return zone;
	}
	public void setZona(Zone zona) {
		this.zone = zona;
	}
}
