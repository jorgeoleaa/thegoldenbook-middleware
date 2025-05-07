package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ReviewCriteria;

public interface ReviewDAO {
	
	public Results<Review> findByReviewCriteria (Connection con, ReviewCriteria reviewCriteria, int pos, int pageSize)
			throws DataException;
	
	public Review findByReview (Connection con, Long userId, Long bookId, String locale) 
			throws DataException;
	
	public Results<Review> findByUser (Connection con, Long userId, String locale, int pos, int pageSize)
			throws DataException;
	
	public Results<Review> findByBook (Connection con, Long bookId, String locale, int pos, int pageSize)
			throws DataException;
	
	public void create (Connection con, Review review)
			throws DataException;
	
	public boolean delete (Connection con, Long userId, Long bookId)
			throws DataException;
	
	public boolean update (Connection con, Review review)
			throws DataException;
	

}
