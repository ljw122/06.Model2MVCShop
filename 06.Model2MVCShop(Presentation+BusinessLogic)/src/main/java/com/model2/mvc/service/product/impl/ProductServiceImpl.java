package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Reply;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	/*Field*/
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;
	
	/*Constructor*/
	public ProductServiceImpl() {
	}
	
	/*Method*/
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.addProduct(product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		
		return productDao.getProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductList(search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
	}

	@Override
	public List<Reply> getProductCommentList(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductCommentList(prodNo);
	}

	@Override
	public void addProductComment(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.insertProductComment(product);
	}

}
