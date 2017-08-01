package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

public class GetPuchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(!HttpUtil.isLogin(request)){
			return HttpUtil.TO_LOGIN_PAGE;
		}
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		PurchaseService purchaseService = (PurchaseService)getServletContext().getAttribute("purchaseServiceImpl");
		Purchase purchase = new Purchase();
		purchase.setTranNo(tranNo);
		purchase = purchaseService.getPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:purchase/getPurchase.jsp";
	}

}
