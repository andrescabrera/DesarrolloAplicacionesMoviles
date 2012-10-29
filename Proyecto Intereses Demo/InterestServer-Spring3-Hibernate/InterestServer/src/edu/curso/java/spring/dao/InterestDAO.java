package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Interest;
import edu.curso.java.spring.bo.Post;

public interface InterestDAO extends GenericDAO<Interest, Long> {
	List<Interest> findByDescription(String interestDescription);
	List<Interest> findAll();
}
