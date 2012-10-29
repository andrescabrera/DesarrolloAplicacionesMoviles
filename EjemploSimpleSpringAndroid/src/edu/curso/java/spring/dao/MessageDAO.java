package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Message;

public interface MessageDAO extends GenericDAO<Message, Long> {
	List<Message> findBySender(Long senderId);
	List<Message> findAll();
}
