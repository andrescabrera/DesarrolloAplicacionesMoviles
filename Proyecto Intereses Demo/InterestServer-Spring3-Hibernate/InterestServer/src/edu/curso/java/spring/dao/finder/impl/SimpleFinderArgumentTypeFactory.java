package edu.curso.java.spring.dao.finder.impl;

import org.hibernate.type.Type;

import edu.curso.java.spring.dao.finder.FinderArgumentTypeFactory;


/**
 * Maps Enums to a custom Hibernate user type
 */
public class SimpleFinderArgumentTypeFactory implements
		FinderArgumentTypeFactory {

	public Type getArgumentType(Object arg) {
		return null;
	}

}
