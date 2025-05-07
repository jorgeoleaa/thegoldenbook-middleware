package com.thegoldenbook.service;

import java.util.Date;

import com.thegoldenbook.model.AbstractCriteria;

public class BookCriteria extends AbstractCriteria{
	
	public static final String ORDER_BY_PRICE = "price";
	public static final String ORDER_BY_DATE = "publication_date";
	public static final String ORDER_BY_LITERARY_GENRE_ID = "literary_genre_id";
	public static final String ORDER_BY_READING_AGE_GROUP_ID = "reading_age_group_id";
	public static final String ORDER_BY_STOCK = "stock";
	
	private Long id;
	private String isbn;
	private String title;
	private Double minPrice;
	private Double maxPrice;
	private Integer minUnits;
	private Integer maxUnits;
	private Date startDate;
	private Date endDate;
	private Integer readingAgeGroupId;
	private Integer languageId;
	private Integer formatId;
	
	private String locale;
	
	private String orderBy = ORDER_BY_PRICE;
	private Boolean ascDesc = Boolean.FALSE;
	
	public BookCriteria() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getMinUnits() {
		return minUnits;
	}

	public void setMinUnits(Integer minUnits) {
		this.minUnits = minUnits;
	}

	public Integer getMaxUnits() {
		return maxUnits;
	}

	public void setMaxUnits(Integer maxUnits) {
		this.maxUnits = maxUnits;
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

	public Integer getReadingAgeGroupId() {
		return readingAgeGroupId;
	}

	public void setReadingAgeGroupId(Integer readingAgeGroupId) {
		this.readingAgeGroupId = readingAgeGroupId;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
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

	public static String getOrderByPrice() {
		return ORDER_BY_PRICE;
	}

	public static String getOrderByDate() {
		return ORDER_BY_DATE;
	}

	public static String getOrderByLiteraryGenreId() {
		return ORDER_BY_LITERARY_GENRE_ID;
	}

	public static String getOrderByReadingAgeGroupId() {
		return ORDER_BY_READING_AGE_GROUP_ID;
	}

	public static String getOrderByStock() {
		return ORDER_BY_STOCK;
	}

	
}
