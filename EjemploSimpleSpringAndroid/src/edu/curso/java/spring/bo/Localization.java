
package edu.curso.java.spring.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

@Entity
@NamedQueries({
@NamedQuery(name="Localization.findAll", query = "from Localization l"),
@NamedQuery(name="Localization.findById", query="from Localization l where l.id = :localizationId")
})
public class Localization {

	private Long id;
	private Double latitude;
	private Double longitude;
	private Set<Post> posts = new HashSet<Post>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany
	@JoinColumn(name="idLocalizacionPublicacion")
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
}
