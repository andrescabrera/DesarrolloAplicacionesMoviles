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
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;
@JsonPropertyOrder({"id","name", "zones"})
@Entity
@NamedQueries({
@NamedQuery(name="Zone.findAll", query = "from Zone z"),
@NamedQuery(name="Zone.findById", query="from Zone z where z.id = :zoneId"),
@NamedQuery(name="Zone.findByName", query="from Zone z where z.name = :zoneName")
})
public class Zone {
	private Long id;
	private String name;
	private Zone zone;
	private Set<Post> posts = new HashSet<Post>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonManagedReference("zona-publicacion")
	@OneToMany
	@JoinColumn(name="idZonaPublicacion")
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
	
	@JsonManagedReference("zona-zonapadre")
	@ManyToOne
	@JoinColumn(name="idZonaPadre")
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
}

