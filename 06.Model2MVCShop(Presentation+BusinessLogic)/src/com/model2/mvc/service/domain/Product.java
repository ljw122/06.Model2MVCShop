package com.model2.mvc.service.domain;

import java.sql.Date;
import java.util.List;


public class Product {
	
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private String proTranCode;
	private int stock;
	private List<Reply> replyList;
	
	public Product(){
	}
	
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		if(proTranCode != null){
			this.proTranCode = proTranCode.trim();
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("Product [");
		if (fileName != null)
			builder.append("fileName=").append(fileName).append(", ");
		if (manuDate != null)
			builder.append("manuDate=").append(manuDate).append(", ");
		builder.append("price=").append(price).append(", ");
		if (prodDetail != null)
			builder.append("prodDetail=").append(prodDetail).append(", ");
		if (prodName != null)
			builder.append("prodName=").append(prodName).append(", ");
		builder.append("prodNo=").append(prodNo).append(", ");
		if (regDate != null)
			builder.append("regDate=").append(regDate).append(", ");
		if (proTranCode != null)
			builder.append("proTranCode=").append(proTranCode).append(", ");
		builder.append("stock=").append(stock).append(", ");
		if (replyList != null)
			builder.append("replyList=").append(replyList.subList(0, Math.min(replyList.size(), maxLen)));
		builder.append("]");
		return builder.toString();
	}

}