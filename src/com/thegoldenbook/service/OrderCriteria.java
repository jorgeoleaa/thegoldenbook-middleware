package com.thegoldenbook.service;

import java.util.Date;

import com.thegoldenbook.model.AbstractCriteria;

public class OrderCriteria extends AbstractCriteria {
	
	public static final String ORDER_BY_DATE = " order_date ";
	public static final String ORDER_BY_PRICE = " price ";
	public static final String ORDER_BY_STATUS = " order_status_id ";
	
	private Long id;
	private Date startDate;
	private Date endDate;
	private Double minPrice;
	private Double maxPrice;
	private Long userId;
	private Integer orderStatusId;
	
	private String orderBy = ORDER_BY_DATE;
	private Boolean ascDesc = Boolean.FALSE;
	
	public OrderCriteria() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
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

	public static String getOrderByPrice() {
		return ORDER_BY_PRICE;
	}

	public static String getOrderByStatus() {
		return ORDER_BY_STATUS;
	}
	
}
