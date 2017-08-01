package com.model2.mvc.common;


public class Search {
	
	/*Field*/
	private int currentPage;
	String searchCondition;
	String searchKeyword;
	String searchKeyword2;
	String orderCondition;
	String orderOption;
	int pageSize;
	int pageUnit;
	boolean stockView;
	
	/*Constructor*/
	public Search(){
	}

	/*Method*/
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchKeyword2() {
		return searchKeyword2;
	}
	public void setSearchKeyword2(String searchKeyword2) {
		this.searchKeyword2 = searchKeyword2;
	}

	public String getOrderCondition() {
		return orderCondition;
	}
	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}

	public String getOrderOption() {
		return orderOption;
	}
	public void setOrderOption(String orderOption) {
		this.orderOption = orderOption;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public boolean isStockView() {
		return stockView;
	}
	public void setStockView(boolean stockView) {
		this.stockView = stockView;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Search [currentPage=").append(currentPage).append(", ");
		if (searchCondition != null)
			builder.append("searchCondition=").append(searchCondition).append(", ");
		if (searchKeyword != null)
			builder.append("searchKeyword=").append(searchKeyword).append(", ");
		if (searchKeyword2 != null)
			builder.append("searchKeyword2=").append(searchKeyword2).append(", ");
		if (orderCondition != null)
			builder.append("orderCondition=").append(orderCondition).append(", ");
		if (orderOption != null)
			builder.append("orderOption=").append(orderOption).append(", ");
		builder.append("pageSize=").append(pageSize).append(", pageUnit=").append(pageUnit).append(", stockView=")
				.append(stockView).append("]");
		return builder.toString();
	}


	
}