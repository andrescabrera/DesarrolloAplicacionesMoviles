package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.User;

public interface UserDAO extends GenericDAO<User, Long>{
	
	List<User> findByName(String userName, String userSurname);
	List<User> findAll();
	
}
