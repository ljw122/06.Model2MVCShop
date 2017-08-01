package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = (User)request.getSession().getAttribute("user");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		purchase.setBuyer(user);
		purchase.setDlvyAddr(request.getParameter("dlvyAddr"));
		purchase.setDlvyDate(request.getParameter("dlvyDate"));
		purchase.setDlvyRequest(request.getParameter("dlvyRequest"));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setPurchaseProd(product);
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setPurchaseCount(Integer.parseInt(request.getParameter("purchaseCount")));
		purchase.setTranCode("1");
		
		((PurchaseService)getServletContext().getAttribute("purchaseServiceImpl")).addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:purchase/addPurchase.jsp";
	}

}
