package com.base.adapter.web.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Controller;

public class MethodAuthorityInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {

		Class<?> clazz = invocation.getThis().getClass();

		if (clazz.isAnnotationPresent(Controller.class)) {
			Controller controller = clazz.getAnnotation(Controller.class);
			String controllerName = controller.value().trim();
			String methodName = invocation.getMethod().getName();

			System.out.println("controllerName:"+controllerName);
			System.out.println("methodName:"+methodName);
		}		
		return invocation.proceed();
	}

}
