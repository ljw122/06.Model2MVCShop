package com.model2.mvc.web.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Reply;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	/*Field*/
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit'] ?: 5}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?: 3}")
	int pageSize;
	
	/*Constructor*/
	public ProductController(){
		System.out.println(getClass());
	}
	
	/*Method*/
	@RequestMapping("addProduct.do")
	public ModelAndView addProduct(@ModelAttribute("product") Product product) throws Exception{
		productService.addProduct(product);
		
		return new ModelAndView("forward:product/addProduct.jsp", "product", product);
	}
	
	@RequestMapping("getProduct.do")
	public ModelAndView getProduct(	@RequestParam("prodNo") int prodNo,
									@RequestParam("menu") String menu,
									@CookieValue(value="history", required=false) String history, 
									HttpServletResponse response) 
														throws Exception{

		Product product = productService.getProduct(prodNo);
		product.setReplyList(productService.getProductCommentList(prodNo));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.addObject("replyList", product.getReplyList());
		
		String viewName = "redirect:updateProductView.do?prodNo="+prodNo;
		
		if(menu.equals("search")){
			String newHistory = prodNo + "";

			if(history != null){
				for(String h : history.split(",")){
					if(!CommonUtil.null2str(h).equals(new Integer(prodNo).toString())){
						newHistory += "," + h;
					}
				}
			}
			history = newHistory;
			
			response.addCookie(new Cookie("history",history));
			
			viewName = "forward:product/getProduct.jsp";
		}
		
		modelAndView.setViewName(viewName);
		
		return modelAndView;
	}
	
	@RequestMapping("updateProduct.do")
	public ModelAndView updateProduct(@ModelAttribute("product") Product product) throws Exception{
		productService.updateProduct(product);
		
		return new ModelAndView("forward:product/getProduct.jsp?menu=manage&prodNo="+product.getProdNo());
	}
	
	@RequestMapping("updateProductView.do")
	public ModelAndView updateProductView( @RequestParam("prodNo") int prodNo) throws Exception{
		Product product = productService.getProduct(prodNo);
		
		return new ModelAndView("forward:product/updateProductView.jsp", "product", product);
	}
	
	
	
	@RequestMapping("listProduct.do")
	public ModelAndView listProduct(@ModelAttribute("search") Search search, @RequestParam("menu") String menu) throws Exception{
		if(search.getCurrentPage()==0){
			search.setCurrentPage(1);
		}
		if(menu.equals("manage")){
			search.setStockView(true);
		}
		search.setPageSize(pageSize);
		search.setPageUnit(pageUnit);
		
		if(search.getSearchCondition() != null && search.getSearchCondition().equals("2")){
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
		
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:product/listProduct.jsp?menu="+menu);
		modelAndView.addObject("list", map.get("list"));
		modelAndView.addObject("resultPage", resultPage);
		
		return modelAndView;
	}
	
	@RequestMapping("addProductComment")
	public ModelAndView addProductComment(@ModelAttribute("product") Product product, @ModelAttribute("reply") Reply reply) throws Exception{
		List<Reply> list = new ArrayList<Reply>();
		
		list.add(reply);
		product.setReplyList(list);
		
		productService.addProductComment(product);
		
		return new ModelAndView("getProduct.do?menu=search");
	}
	
}
