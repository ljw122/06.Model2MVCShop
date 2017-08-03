package com.model2.mvc.web.purchase;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@Controller
public class PurchaseController {

	/*Field*/
	@Autowired
	@Qualifier("purchaseService")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Value("#{commonProperties['pageUnit'] ?: 5}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?: 3}")
	int pageSize;
	
	/*Constructor*/
	public PurchaseController(){
		System.out.println(getClass());
	}
	
	/*Method*/
	@RequestMapping("addPurchaseView.do")
	public String addPurchaseView(@RequestParam("prodNo") int prodNo, Model model) throws Exception{
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		return "forward:purchase/addPurchaseView.jsp";
	}
	
	@RequestMapping("addPurchase.do")
	public String addPurchase(	@ModelAttribute("purchase") Purchase purchase	) throws Exception{

		purchaseService.addPurchase(purchase);
		
		return "forward:purchase/addPurchase.jsp";
	}
	
	@RequestMapping("getPurchase.do")
	public String getPurchase(	@ModelAttribute("purchase") Purchase purchase,
								Model model	)throws Exception{
		purchase = purchaseService.getPurchase(purchase);
		model.addAttribute("purchase", purchase);
		
		return "forward:purchase/getPurchase.jsp";
	}
	
	@RequestMapping("updatePurchaseView.do")
	public String updatePurchaseView(@ModelAttribute("purchase") Purchase purchase, Model model) throws Exception{
		purchase = purchaseService.getPurchase(purchase);
		model.addAttribute("purchase", purchase);
		
		return "forward:purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("updatePurchase.do")
	public String updatePurchase(	@ModelAttribute("purchase") Purchase purchase	) throws Exception{
		
		purchaseService.updatePurchase(purchase);
		
		return "forward:getPurchase.do";
	}
	
	@RequestMapping("listPurchase.do")
	public String listPurchase(	@ModelAttribute("search") Search search,
								Model model		) throws Exception{
		
		this.getList(search, model);
		
		return "forward:purchase/listPurchase.jsp";
	}
	
	@RequestMapping("listSale.do")
	public String listSale(	@ModelAttribute("search") Search search,
							Model model) throws Exception{
		
		this.getList(search, model);
		
		return "forward:purchase/listSale.jsp";
	}

	@RequestMapping("updateTranCode.do")
	public String updateTranCode(	@RequestParam("menu") String menu,
									@ModelAttribute("purchase") Purchase purchase	) throws Exception{
		
		Purchase updatePurchase = purchaseService.getPurchase(purchase);
		updatePurchase.setTranCode(purchase.getTranCode());
		
		purchaseService.updatePurchase(updatePurchase);
		
		if(menu.equals("manage")){
			return "forward:listSale.do?searchKeyword=saleList";
		}else{
			return "forward:listPurchase.do?searchCondition="+purchase.getBuyer().getUserId()+"&searchKeyword=purchaseList";
		}
	}
	
	
	
	private void getList(Search search, Model model) throws Exception{

		if(search.getCurrentPage()==0){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		search.setPageUnit(pageUnit);

		Map<String, Object> map = purchaseService.getPurchaseList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
	}
	
}
