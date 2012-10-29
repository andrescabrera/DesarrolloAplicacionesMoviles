package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Post;

public interface PostDAO extends GenericDAO<Post, Long> {
	
	List<Post> findAll();
	List<Post> findByText(String text);
	List<Post> findByInterest(Long interestId);	
}
