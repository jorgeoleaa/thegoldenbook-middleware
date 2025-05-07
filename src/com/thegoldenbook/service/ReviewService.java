package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;

public interface ReviewService {
	
	public Results<Review> findByReviewCriteria(ReviewCriteria reviewCriteria, int pos, int pageSize)
			throws DataException;
	
	public Review findByReview (Long userId, Long bookId, String locale) 
			throws DataException;
	
	public Results<Review> findByUser(Long userId, int pos, int pageSize, String locale)
			throws DataException;
	
	public Results<Review> findByBook(Long bookId, int pos, int pageSize, String locale)
			throws DataException;
	
	public void create (Review review, String locale)
			throws DataException;
	
	public boolean delete (Long userId, Long bookId)
			throws DataException;
	
	public boolean update (Review review)
			throws DataException;
	
	public Double calculateAverage (List<Review> reviews) 
		throws DataException;
	
}
