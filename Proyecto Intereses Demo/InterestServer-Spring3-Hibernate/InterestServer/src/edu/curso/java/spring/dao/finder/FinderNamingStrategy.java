package edu.curso.java.spring.dao.finder;

import java.lang.reflect.Method;
/**
 * Used to locate a named query based on the called finder method
 */
public interface FinderNamingStrategy {
    @SuppressWarnings("rawtypes")
	public String queryNameFromMethod(Class findTargetType, Method finderMethod);
}
