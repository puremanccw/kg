package com.charles.common.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public abstract class BaseInterceptor implements MethodInterceptor {
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		//排除原生方法
		Method[] methods = Object.class.getMethods();
		
		for(Method method : methods) {
			if(invocation.getMethod().equals(method)) {
				return null;
			}
		}
		return businessInvoke(invocation);
	}
	
	/**
	 * 子拦截器
	 * @param invocation
	 * @return
	 * @throws Throwable
	 */
	public abstract Object businessInvoke(MethodInvocation invocation) throws Throwable;
}
