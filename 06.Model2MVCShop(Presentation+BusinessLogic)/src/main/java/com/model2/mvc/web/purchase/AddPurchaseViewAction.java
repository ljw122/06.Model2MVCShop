package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(!HttpUtil.isLogin(request)){
			return HttpUtil.TO_LOGIN_PAGE;
		}
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		Product product = null;
		ProductService productService = (ProductService)getServletContext().getAttribute("productServiceImpl");
		
		product = productService.getProduct(prodNo);
		
		request.setAttribute("product", product);
		return "forward:purchase/addPurchaseView.jsp";
	}

}
