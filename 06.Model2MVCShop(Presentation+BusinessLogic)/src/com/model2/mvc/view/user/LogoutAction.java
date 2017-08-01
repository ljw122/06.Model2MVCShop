package com.model2.mvc.view.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;


public class LogoutAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		session.invalidate();

		//history 목록 삭제
		Cookie cookie = new Cookie("history","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return "redirect:index.jsp";
	}
}