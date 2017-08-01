package com.model2.mvc.web.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));

		ProductService productService = (ProductService)getServletContext().getAttribute("productServiceImpl");
		Product product = productService.getProduct(prodNo);
		product.setReplyList(productService.getProductCommentList(prodNo));
		
		request.setAttribute("product", product);
		request.setAttribute("replyList", product.getReplyList());
		
		if(request.getParameter("menu").equals("manage")){
			return "redirect:updateProductView.do?prodNo="+prodNo;
			
		} else {
			Cookie[] cookies = request.getCookies();
			String history = null;
			if(cookies != null){
				for(Cookie c : cookies){
					if(c.getName().equals("history")){
						history = c.getValue();
					}
				}
			}
			
			if(history != null){
				String newHistory = prodNo+"";
				for(String h : history.split(",")){
					if(!CommonUtil.null2str(h).equals(new Integer(prodNo).toString())){
						newHistory += ","+h;
					}
				}
				history = newHistory;
			}else{
				history = ""+prodNo;
			}
			response.addCookie(new Cookie("history",history));
			
			return "forward:product/getProduct.jsp";
		}
	}

}
