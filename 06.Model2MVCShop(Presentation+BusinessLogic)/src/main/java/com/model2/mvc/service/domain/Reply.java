package com.model2.mvc.service.domain;

import java.sql.Date;

public class Reply {
	/*Field*/
	private int replyNo;
	private String userId;
	private String cmt;
	private Date regDate;
	private Date updDate;
	
	/*Constructor*/
	public Reply(){
	}

	/*Method*/
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reply [replyNo=").append(replyNo).append(", ");
		if (userId != null)
			builder.append("userId=").append(userId).append(", ");
		if (cmt != null)
			builder.append("cmt=").append(cmt).append(", ");
		if (regDate != null)
			builder.append("regDate=").append(regDate).append(", ");
		if (updDate != null)
			builder.append("updDate=").append(updDate);
		builder.append("]");
		return builder.toString();
	}

	
}
