package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
	
	/*Field*/
	@Autowired
	@Qualifier("purchaseDao")
	private PurchaseDao purchaseDao;
	
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;
	
	/*Constructor*/
	public PurchaseServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/*Method*/
	public void setPurchaseDAO(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public void setProductDAO(ProductDao productDao) {
		this.productDao = productDao;
	}

	
	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.getPurchase(purchase);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.getPurchaseList(search);
	}

	@Override
	public void updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updatePurchase(purchase);
	}

}
