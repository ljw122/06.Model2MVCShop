package com.model2.mvc.web.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Reply;
import com.model2.mvc.service.product.ProductService;

public class AddProductCommentAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		Reply reply = new Reply();
		Product product = new Product();
		List<Reply> list = new ArrayList<Reply>();
		
		reply.setCmt(request.getParameter("cmt"));
		reply.setUserId(request.getParameter("userId"));
		list.add(reply);
		
		product.setProdNo(prodNo);
		product.setReplyList(list);
		
		ProductService productService = (ProductService)getServletContext().getAttribute("productServiceImpl");
		productService.addProductComment(product);
		
		return "getProduct.do?prodNo="+prodNo+"&menu=search";
	}

}
