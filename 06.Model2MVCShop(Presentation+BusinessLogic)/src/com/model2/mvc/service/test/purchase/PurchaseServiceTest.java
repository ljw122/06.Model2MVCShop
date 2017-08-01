package com.model2.mvc.service.test.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/commonservice.xml"})
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	
	//@Test
	public void testAddPurchase() throws Exception{
		System.out.println("hi");
		Purchase p = new Purchase();
		Product pr = new Product();
		pr.setProdNo(10070);
		
		User u = new User();
		u.setUserId("user01");
		
		p.setBuyer(u);
		p.setPurchaseProd(pr);
		p.setDlvyAddr("주소주소");
		p.setDlvyDate("2007-09-08");
		p.setDlvyRequest("빠른배소오송");
		p.setReceiverName("이르르으으음");
		p.setReceiverPhone("010-0101-2222");
		p.setPurchaseCount(5);
		p.setTranCode("1");
		p.setPaymentOption("1");
		
		purchaseDao.addPurchase(p);
	}
	
	//@Test
	public void testGetPurchase() throws Exception{
		System.out.println("hi");
		Purchase p = new Purchase();
		p.setPurchaseProd(new Product());
		p.setTranNo(10000);
		//p.getPurchaseProd().setProdNo(10069);
		System.out.println(purchaseDao.getPurchase(p));
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception{
		System.out.println("hi");
		Purchase p = new Purchase();
		p.setTranNo(10022);
		p = purchaseDao.getPurchase(p);
		System.out.println("\n==============================");
		System.out.println(p);
		System.out.println("==============================\n");
		p.setReceiverName("너님");
		p.setPurchaseCount(3);
		
		purchaseDao.updatePurchase(p);
		
	}
	
	//@Test
	public void testGetPurchaseList() throws Exception{
		//SearchCondition에 buyerId 담아서 보내야함!!!!!!!!!!!!!
		//SearchKeyword에 purchaseList 담아서 보내야함!!!!!!!!!!!!
		System.out.println("hi");
		Purchase p = new Purchase();
		p.setBuyer(new User());
		p.getBuyer().setUserId("user01");
		
		Search s = new Search();
		s.setCurrentPage(1);
		s.setPageSize(3);
		s.setPageUnit(5);
		s.setSearchCondition(p.getBuyer().getUserId());
		s.setSearchKeyword("purchaseList");
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("\n=====================================");
		map = purchaseDao.getPurchaseList(s);
		
		for(Purchase lp : (List<Purchase>)map.get("list")){
			System.out.print(lp);
			System.out.println(" "+lp.getPurchaseCount());
		}
		System.out.println("=====================================\n");
	}
	
	@Test
	public void testGetSaleList() throws Exception{
		//SearchCondition에 buyerId 담아서 보내야함!!!!!!!!!!!!!
		//SearchKeyword에 purchaseList 담아서 보내야함!!!!!!!!!!!!
		System.out.println("hi");
		Purchase p = new Purchase();
		
		Search s = new Search();
		s.setCurrentPage(1);
		s.setPageSize(3);
		s.setPageUnit(5);
		s.setSearchKeyword("saleList");
		p.setTranNo(10022);
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("\n=====================================");
		map = purchaseDao.getPurchaseList(s);
		
		for(Purchase lp : (List<Purchase>)map.get("list")){
			System.out.print(lp);
			System.out.println(" "+lp.getPurchaseCount());
		}
		System.out.println("=====================================\n");
	}
}
