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
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;
@JsonPropertyOrder({"id", "description","posts","interests"})
@Entity
@NamedQueries({
@NamedQuery(name="Interest.findAll", query = "from Interest i where i.interesPadre is null"),
@NamedQuery(name="Interest.findById", query="from Interest i where i.id = :interestId"),
@NamedQuery(name="Interest.findByDescription", query="from Interest i where i.description LIKE :interestDescription")
})
public class Interest {
	
	private Long id;
	private String description;
	private Interest interesPadre;
	private Set<Interest> interesesHijos = new HashSet<Interest>();
	private Set<Post> posts = new HashSet<Post>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonManagedReference("intereses-publicacion")
	@OneToMany
	@JoinColumn(name="idInteresPublicacion")
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	@JsonBackReference("intereses-relacionados")
	@ManyToOne
	@JoinColumn(name="idInteresRelacionado")
	public Interest getInteresPadre() {
		return interesPadre;
	}
	public void setInteresPadre(Interest interesPadre) {
		this.interesPadre = interesPadre;
	}
	
	@JsonManagedReference("idInteresRelacionado")
	@OneToMany
	@JoinColumn(name="idInteresRelacionado")
	public Set<Interest> getInteresesHijos() {
		return interesesHijos;
	}
	public void setInteresesHijos(Set<Interest> interesesHijos) {
		this.interesesHijos = interesesHijos;
	}
}