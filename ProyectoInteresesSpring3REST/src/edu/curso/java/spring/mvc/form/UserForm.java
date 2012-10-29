package edu.curso.java.spring.mvc.form;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

public class UserForm {
	
	private Long id;
	@NotEmpty
	@Size(min = 3, max = 50)
	private String name;
	@NotEmpty
	@Size(min = 3, max = 50)
	private String surname;
    @DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateOfBirth = new Date();
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}    
}
