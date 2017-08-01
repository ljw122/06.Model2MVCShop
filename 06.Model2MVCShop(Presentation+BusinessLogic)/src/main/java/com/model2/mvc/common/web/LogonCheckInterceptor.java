package com.model2.mvc.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.model2.mvc.service.domain.User;


public class LogonCheckInterceptor extends HandlerInterceptorAdapter {

	public LogonCheckInterceptor(){
		System.out.println("==> "+getClass().getName()+" default Constructor call......");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("\n:: [logonCheckInterceptor start....]");
		
		HttpSession session = request.getSession();
		User sessionUser = null;
		
		if( (sessionUser = (User)session.getAttribute("sessionUser")) == null){
			sessionUser = new User();
		}
		
		if(sessionUser.isActive()){
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction") != -1 || uri.indexOf("logon") != -1){
				request.getRequestDispatcher("user002/home.jsp").forward(request, response);
				System.out.println("[�α��� ����... �α��� �� ���ʿ��� �䱸....");
				System.out.println("[logonCheckInterceptor end......]\n");
				return false;
			}
			
			System.out.println("[�α��� ����....]");
			System.out.println("[logonCheckInterceptor end......]\n");
			return true;
		}else{
			
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction") != -1 || uri.indexOf("logon") != -1){
				System.out.println("[�α��� �õ� ����....]");
				System.out.println("[logonCheckInterceptor end......]\n");
				return true;
			}
			
			request.getRequestDispatcher("user002/logon.jsp").forward(request, response);
			System.out.println("[�α��� ����....]");
			System.out.println("[logonCheckInterceptor end......]\n");
			return false;
		}
		
		
	}
	
	
}
