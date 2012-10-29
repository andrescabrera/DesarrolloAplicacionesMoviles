package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Localization;

public interface LocalizationDAO extends GenericDAO<Localization, Long> {
	List<Localization> findAll();
}
