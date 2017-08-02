package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDao")
public class PurchaseDaoImpl implements PurchaseDao {

	/*Field*/
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	/*Constructor*/
	public PurchaseDaoImpl(){
	}
	
	/*Method*/
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public Purchase getPurchase(Purchase purchase) throws Exception{
		
		return sqlSession.selectOne("PurchaseMapper.getPurchase",purchase);
	}
	
	
	public Map<String, Object> getPurchaseList(Search search) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("totalCount", sqlSession.selectOne("PurchaseMapper.getTotalCount", search));
		map.put("list", sqlSession.selectList("PurchaseMapper.getPurchaseList", search));
		
		return map;
	}
		
	public void addPurchase(Purchase purchase) throws Exception{

		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
		
		for(int i=1;i<purchase.getPurchaseCount();i++){
			sqlSession.insert("PurchaseMapper.addPurchaseCount", purchase);
		}
	}
	
	public void updatePurchase(Purchase purchase) throws Exception{
		
		sqlSession.delete("PurchaseMapper.deletePurchaseForUpdate", purchase.getTranNo());
		
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
		
		for(int i=1;i<purchase.getPurchaseCount();i++){
			sqlSession.insert("PurchaseMapper.insertPurchaseForUpdate", purchase);
		}
	}
		
}
