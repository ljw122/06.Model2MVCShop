package com.model2.mvc.web.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

public class UpdateProductViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService productService = (ProductService)getServletContext().getAttribute("productServiceImpl");
		Product product = productService.getProduct(prodNo);
		
		request.setAttribute("product", product);
		
		return "forward:product/updateProductView.jsp";
	}

}
