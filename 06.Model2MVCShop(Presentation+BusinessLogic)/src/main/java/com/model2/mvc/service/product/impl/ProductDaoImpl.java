package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Reply;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao{
	/*Field*/
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	/*Constructor*/
	public ProductDaoImpl(){
	}

	/*Method*/
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct",product);
	}

	public Product getProduct(int prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct",prodNo);
	}

	public Map<String, Object> getProductList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", sqlSession.selectOne("ProductMapper.getTotalCount",search));
		map.put("list", sqlSession.selectList("ProductMapper.getProductList", search));
		return map;
	}

	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct",product);
	}
	
	public List<Reply> getProductCommentList(int prodNo) throws Exception{
		
		return sqlSession.selectList("ProductMapper.getProductCommentList", prodNo);
	}
	
	public void insertProductComment(Product product) throws Exception{
		
		sqlSession.insert("ProductMapper.insertProductComment", product);
	}
}
