package com.lanou.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 拦截器   文件配置  配置在spring-mvc中
 * @author 就是我
 *  拦截器接口HandlerInterceptor
 */
public class ImerInterceptor implements HandlerInterceptor{
	
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("WatchExecuteTime");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long beginTime = System.currentTimeMillis();			//开始时间
		startTimeThreadLocal.set(beginTime);
		System.out.println("拦截器开始时间"+beginTime);
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTimeThreadLocal.get();
		System.out.println(String.format("%s execute %d ms." , request.getRequestURI() , executeTime));
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
