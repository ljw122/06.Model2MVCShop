package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Purchase purchase = new Purchase();
		User user = (User)request.getSession().getAttribute("user");
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		purchase.setDlvyAddr(request.getParameter("dlvyAddr"));
		purchase.setDlvyDate(request.getParameter("dlvyDate"));
		purchase.setDlvyRequest(request.getParameter("dlvyRequest"));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setPurchaseCount(Integer.parseInt(request.getParameter("purchaseCount")));
		purchase.setTranNo(tranNo);
		purchase.setBuyer(user);
		
		PurchaseService puchaseService = (PurchaseService)getServletContext().getAttribute("purchaseServiceImpl");
		puchaseService.updatePurchase(purchase);
		
		return "forward:./getPurchase.do?tranNo="+tranNo;
	}

}
