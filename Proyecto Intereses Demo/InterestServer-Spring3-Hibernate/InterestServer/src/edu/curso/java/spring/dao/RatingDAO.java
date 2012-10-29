package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Rating;

public interface RatingDAO extends GenericDAO<Rating, Long> {
	List<Rating> findAll();
}
