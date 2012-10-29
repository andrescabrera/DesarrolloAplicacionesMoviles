package edu.curso.java.spring.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

@Entity
@NamedQueries({
@NamedQuery(name="Rating.findAll", query = "from Rating r"),
@NamedQuery(name="Rating.findById", query="from Rating r where r.id = :ratingId")
})
public class Rating {
	private Long id;
	private int ratingPoints;
	private Post post;
	private User user;
	
	@Id
	@GeneratedValue
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
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idPostCalificado")
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idUsuarioCalificacion")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
