package com.model2.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class RequestFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain filterChain) 
																						throws IOException, ServletException {
		request.setCharacterEncoding("euc-kr");
		filterChain.doFilter(request, response);
	}

	public void destroy() {
	}
}