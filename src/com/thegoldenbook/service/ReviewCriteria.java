package com.thegoldenbook.service;

import java.util.Date;

import com.thegoldenbook.model.AbstractCriteria;

public class ReviewCriteria extends AbstractCriteria{
	
	public static final String ORDER_BY_DATE = "published_date";
	
	private Long userId;
	private Long bookId;
	private Date startDate;
	private Date endDate;
	private String content;
	
	private String orderBy = ORDER_BY_DATE;
	private Boolean ascDesc = Boolean.FALSE;
	
	public ReviewCriteria() {
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Boolean getAscDesc() {
		return ascDesc;
	}

	public void setAscDesc(Boolean ascDesc) {
		this.ascDesc = ascDesc;
	}

	public static String getOrderByDate() {
		return ORDER_BY_DATE;
	}

	

}
