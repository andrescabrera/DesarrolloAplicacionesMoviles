package edu.curso.java.spring.dao;

import java.io.Serializable;

/**
 * The basic GenericDao interface with CRUD methods
 * Finders are added with interface inheritance and AOP introductions for concrete implementations
 *
 * Extended interfaces may declare methods starting with find... list... iterate... or scroll...
 * They will execute a preconfigured query that is looked up based on the rest of the method name
 */
public interface GenericDAO <T, PK extends Serializable>  {
	    PK create(T object);
	    T read(PK id);
	    void update(T object);
	    void delete(T object);
}
