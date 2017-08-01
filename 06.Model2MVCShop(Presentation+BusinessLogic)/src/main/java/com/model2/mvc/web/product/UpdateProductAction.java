package com.model2.mvc.web.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int prodNo = new Integer(request.getParameter("prodNo")).intValue();

		Product product = new Product();
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		product.setStock(Integer.parseInt(request.getParameter("stock")));
		
		ProductService productService = (ProductService)getServletContext().getAttribute("productServiceImpl");
		productService.updateProduct(product);
		product=productService.getProduct(prodNo);
		
		request.setAttribute("product", product);
		
		
		return "forward:product/getProduct.jsp?menu=manage&prodNo="+prodNo;
	}

}
