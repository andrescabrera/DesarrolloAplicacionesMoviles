package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Zone;

public interface ZoneDAO extends GenericDAO<Zone, Long>{
	List<Zone> findByName(String zoneName);
	List<Zone> findAll();
}
