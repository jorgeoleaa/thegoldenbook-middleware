package com.thegoldenbook.model;

import java.util.Date;

public class Review extends AbstractValueObject{
	
	private Long bookId;
	private String bookName;
	private Long authorId;
	private String authorName;
	private String authorLastName;
	private String authorSecondLastName;
	private Long userId;
	private String nickname;
	private String userName;
	private String userLastName;
	private String userSecondLastName;
	private Double rating;
	private String subject;
	private String body;
	private Date publicationDate;
	private Integer languageId;
	
	public Review () {
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getAuthorSecondLastName() {
		return authorSecondLastName;
	}

	public void setAuthorSecondLastName(String authorSecondLastName) {
		this.authorSecondLastName = authorSecondLastName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserSecondLastName() {
		return userSecondLastName;
	}

	public void setUserSecondLastName(String userSecondLastName) {
		this.userSecondLastName = userSecondLastName;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	
}
