package edu.curso.java.spring.dao.finder.impl;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

import edu.curso.java.spring.dao.finder.FinderExecutor;

/**
 * Connects the Spring AOP magic with the Hibernate DAO magic
 * For any method beginning with "find" this interceptor will use the FinderExecutor to call a Hibernate named query
 */
public class FinderIntroductionInterceptor implements IntroductionInterceptor {

	 public Object invoke(MethodInvocation methodInvocation) throws Throwable {

	        @SuppressWarnings("rawtypes")
			FinderExecutor genericDao = (FinderExecutor) methodInvocation.getThis();

	        String methodName = methodInvocation.getMethod().getName();
	        if (methodName.startsWith("find")) {
	            Object[] arguments = methodInvocation.getArguments();
	            return genericDao.executeFinder(methodInvocation.getMethod(), arguments);
	        } else {
	            return methodInvocation.proceed();
	        }
	    }

	    @SuppressWarnings("rawtypes")
		public boolean implementsInterface(Class intf) {
	        return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
	    }

}
