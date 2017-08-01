package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;

public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Search search = new Search();

		int currentPage = 1;
		if(request.getParameter("currentPage") != null && !CommonUtil.null2str(request.getParameter("currentPage")).equals(""))
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		if(request.getParameter("stockView") != null || request.getParameter("menu").equals("manage"))
			search.setStockView(true);
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		search.setSearchKeyword2(request.getParameter("searchKeyword2"));
		search.setOrderOption(request.getParameter("orderOption"));
		search.setOrderCondition(request.getParameter("orderCondition"));
		
		if (search.getSearchCondition() != null && search.getSearchCondition().equals("2")) {
			try{
				Integer.parseInt(search.getSearchKeyword());
			}catch(NumberFormatException e){
				search.setSearchKeyword("");
			}
			try{
				Integer.parseInt(search.getSearchKeyword2());
			}catch(NumberFormatException e){
				search.setSearchKeyword2("");
			}
		}
		
		int pageSize = Integer.parseInt(request.getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(request.getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		search.setPageUnit(pageUnit);

		ProductService productService = (ProductService)getServletContext().getAttribute("productServiceImpl");
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		
		return "forward:product/listProduct.jsp?menu="+request.getParameter("menu");
	}

}
