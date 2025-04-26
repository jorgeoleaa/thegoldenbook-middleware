package com.thegoldenbook.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book extends AbstractValueObject {
	
	private Long id;
	private String isbn;
	private String title;
	private String synopsis;
	private Date publicationDate;
	private Double averageRating;
	private Integer stock;
	private Double price;
	private Integer literaryGenreId;
	private String literaryGenreName;
	private Integer readingAgeGropuId;
	private String readingAgeGroupName;
	private Integer languageId;
	private String languageName;
	private Integer formatId;
	private String formatName;
	private List<Author> authors;
	private List<Subject> subjects;

	
	public Book() {
		authors = new ArrayList<Author>();
		subjects = new ArrayList<Subject>();
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


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	public Date getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}


	public Double getAverageRating() {
		return averageRating;
	}


	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getLiteraryGenreId() {
		return literaryGenreId;
	}


	public void setLiteraryGenreId(Integer literaryGenreId) {
		this.literaryGenreId = literaryGenreId;
	}


	public String getLiteraryGenreName() {
		return literaryGenreName;
	}


	public void setLiteraryGenreName(String literaryGenreName) {
		this.literaryGenreName = literaryGenreName;
	}


	public Integer getReadingAgeGropuId() {
		return readingAgeGropuId;
	}


	public void setReadingAgeGropuId(Integer readingAgeGropuId) {
		this.readingAgeGropuId = readingAgeGropuId;
	}


	public String getReadingAgeGroupName() {
		return readingAgeGroupName;
	}


	public void setReadingAgeGroupName(String readingAgeGroupName) {
		this.readingAgeGroupName = readingAgeGroupName;
	}


	public Integer getLanguageId() {
		return languageId;
	}


	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}


	public String getLanguageName() {
		return languageName;
	}


	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}


	public Integer getFormatId() {
		return formatId;
	}


	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}


	public String getFormatName() {
		return formatName;
	}


	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}


	public List<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}


	public List<Subject> getSubjects() {
		return subjects;
	}


	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	
}