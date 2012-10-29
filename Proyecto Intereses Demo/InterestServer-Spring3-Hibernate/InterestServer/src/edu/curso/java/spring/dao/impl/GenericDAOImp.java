package edu.curso.java.spring.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import edu.curso.java.spring.dao.GenericDAO;
import edu.curso.java.spring.dao.finder.FinderArgumentTypeFactory;
import edu.curso.java.spring.dao.finder.FinderExecutor;
import edu.curso.java.spring.dao.finder.FinderNamingStrategy;
import edu.curso.java.spring.dao.finder.impl.SimpleFinderArgumentTypeFactory;
import edu.curso.java.spring.dao.finder.impl.SimpleFinderNamingStrategy;
/**
 * Hibernate implementation of GenericDao
 * A typesafe implementation of CRUD and finder methods based on Hibernate and Spring AOP
 * The finders are implemented through the executeFinder method. Normally called by the FinderIntroductionInterceptor
 */
@SuppressWarnings("rawtypes")
public class GenericDAOImp<T, PK extends Serializable> implements
		GenericDAO<T, PK>, FinderExecutor {
	private Class<T> type;

	private SessionFactory sessionFactory;
    private FinderNamingStrategy namingStrategy = new SimpleFinderNamingStrategy(); // Default. Can override in config
    private FinderArgumentTypeFactory argumentTypeFactory = new SimpleFinderArgumentTypeFactory(); // Default. Can override in config
	
//    public Long getIdentifier(){
//    	getSession().getIdentifier(arg0)
//    }
    
    public GenericDAOImp(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public PK create(T b) {
		return (PK) getSession().save(b);
	}

	@SuppressWarnings("unchecked")
	public T read(PK id) {
		return (T) getSession().get(type, id);
	}

	public void update(T b) {
		getSession().update(b);
	}

	public void delete(T b) {
		getSession().delete(b);
	}

	public List<T> executeFinder(Method method, final Object[] queryArgs) {
		final Query namedQuery = prepareQuery(method, queryArgs);
		return (List<T>) namedQuery.list();
	}

	private Query prepareQuery(Method method, Object[] queryArgs)
    {
        final String queryName = getNamingStrategy().queryNameFromMethod(type, method);
        final Query namedQuery = getSession().getNamedQuery(queryName);
        String[] namedParameters = namedQuery.getNamedParameters();
        if(namedParameters.length==0)
        {
            setPositionalParams(queryArgs, namedQuery);
        } else {
            setNamedParams(namedParameters, queryArgs, namedQuery);
        }
        return namedQuery;
    }
	
	private void setNamedParams(String[] namedParameters, Object[] queryArgs, Query namedQuery)
    {
        // Set parameter. Use custom Hibernate Type if necessary
        if(queryArgs!=null)
        {
            for(int i = 0; i < queryArgs.length; i++)
            {
                Object arg = queryArgs[i];
                Type argType = getArgumentTypeFactory().getArgumentType(arg);
                if(argType != null)
                {
                    namedQuery.setParameter(namedParameters[i], arg, argType);
                }
                else
                {
                    if(arg instanceof Collection) {
                        namedQuery.setParameterList(namedParameters[i], (Collection) arg);
                    }
                    else
                    {
                        namedQuery.setParameter(namedParameters[i], arg);
                    }
                }
            }
        }
    }

	private void setPositionalParams(Object[] queryArgs, Query namedQuery)
    {
        // Set parameter. Use custom Hibernate Type if necessary
        if(queryArgs!=null)
        {
            for(int i = 0; i < queryArgs.length; i++)
            {
                Object arg = queryArgs[i];
                Type argType = getArgumentTypeFactory().getArgumentType(arg);
                if(argType != null)
                {
                    namedQuery.setParameter(i, arg, argType);
                }
                else
                {
                    namedQuery.setParameter(i, arg);
                }
            }
        }
    }

	public FinderArgumentTypeFactory getArgumentTypeFactory()
    {
        return argumentTypeFactory;
    }
	
	public void setArgumentTypeFactory(FinderArgumentTypeFactory argumentTypeFactory)
    {
        this.argumentTypeFactory = argumentTypeFactory;
    }

	public FinderNamingStrategy getNamingStrategy()
    {
        return namingStrategy;
    }
    
    public void setNamingStrategy(FinderNamingStrategy namingStrategy)
    {
        this.namingStrategy = namingStrategy;
    }

	public String queryNameFromMethod(Method finderMethod) {
		return type.getSimpleName() + "." + finderMethod.getName();
	}

	@SuppressWarnings("unchecked")
	@Override
    public Iterator<T> iterateFinder(Method method, final Object[] queryArgs)
    {
        final Query namedQuery = prepareQuery(method, queryArgs);
        return (Iterator<T>) namedQuery.iterate();
    }
	
    public Session getSession()
    {
        boolean allowCreate = true;
        return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
    }
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
//  public ScrollableResults scrollFinder(Method method, final Object[] queryArgs)
//  {
//      final Query namedQuery = prepareQuery(method, queryArgs);
//      return (ScrollableResults) namedQuery.scroll();
//  }
}
