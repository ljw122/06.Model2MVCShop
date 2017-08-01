package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.user.UserService;


public class CheckDuplicationAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String userId=request.getParameter("userId");
		
		UserService userService=(UserService)getServletContext().getAttribute("userServiceImpl");
		boolean result=userService.checkDuplication(userId);
		
		request.setAttribute("result",new Boolean(result) );
		request.setAttribute("userId", userId);
		
		return "forward:user/checkDuplication.jsp";
	}
}