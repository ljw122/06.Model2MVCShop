package com.model2.mvc.web.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode");
		
		PurchaseService purchaseService = (PurchaseService)getServletContext().getAttribute("purchaseServiceImpl");
		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
		purchase = purchaseService.getPurchase(purchase);
		purchase.setTranCode(tranCode);
		
		purchaseService.updatePurchase(purchase);
		
		if(request.getParameter("menu").equals("manage")){
			return "forward:listSale.do";
		}else{
			return "forward:listPurchase.do";
		}
	}

}
