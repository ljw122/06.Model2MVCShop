package com.model2.mvc.common.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.domain.User;


public class HttpUtil {
	
	/*Field*/
	public static final String TO_LOGIN_PAGE = "redirect:/user/loginView.jsp";
	
	/*Constructor*/
	public HttpUtil(){
	}
	
	/*Method*/
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path){
		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}catch(Exception ex){
			System.out.println("forward 오류 : " + ex);
			throw new RuntimeException("forward 오류 : " + ex);
		}
	}
	
	public static void redirect(HttpServletResponse response, String path){
		try{
			response.sendRedirect(path);
		}catch(Exception ex){
			System.out.println("redirect 오류 : " + ex);
			throw new RuntimeException("redirect 오류  : " + ex);
		}
	}
	
	public static boolean isLogin(HttpServletRequest request){
		if( (User)request.getSession().getAttribute("user")==null ){
			System.out.println("user 세션이 없습니다!");
			return false;
		} else {
			return true;
		}
	}
}