package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.HttpUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;

public class ListSaleAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Search search = new Search();
		if(!HttpUtil.isLogin(request)){
			return HttpUtil.TO_LOGIN_PAGE;
		}
		
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null && !CommonUtil.null2str(request.getParameter("currentPage")).equals("")){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		search.setCurrentPage(currentPage);
		
		int pageSize = Integer.parseInt(request.getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(request.getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		search.setPageUnit(pageUnit);
		search.setSearchKeyword("saleList");

		PurchaseService purchaseService = (PurchaseService)getServletContext().getAttribute("purchaseServiceImpl");
		Map<String, Object> map = purchaseService.getPurchaseList(search);
		
		Page resultPage = new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);

		return "forward:purchase/listSale.jsp";
	}

}
