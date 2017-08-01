package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);

		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[] {"/config/context-common.xml",
										"/config/context-aspect.xml",
										"/config/context-mybatis.xml",
										"/config/context-transaction.xml"});

		getServletContext().setAttribute("userServiceImpl", context.getBean("userServiceImpl"));
		getServletContext().setAttribute("productServiceImpl", context.getBean("productServiceImpl"));
		getServletContext().setAttribute("purchaseServiceImpl", context.getBean("purchaseServiceImpl"));
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String requestPath = url.substring(contextPath.length());
		System.out.println("\nActionServlet.service() RequestURI : "+requestPath);
		
		try{
			Action action = mapper.getAction(requestPath);
			action.setServletContext(getServletContext());
			
			String resultPage=action.execute(request, response);
			String path=resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, path);
			else
				HttpUtil.redirect(response, path);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}