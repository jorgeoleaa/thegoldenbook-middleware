package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.BookDAO;
import com.thegoldenbook.dao.ReviewDAO;
import com.thegoldenbook.dao.impl.BookDAOImpl;
import com.thegoldenbook.dao.impl.ReviewDAOImpl;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ReviewCriteria;
import com.thegoldenbook.service.ReviewService;
import com.thegoldenbook.util.JDBCUtils;

public class ReviewServiceImpl implements ReviewService {

	private static Logger logger = LogManager.getLogger(ReviewServiceImpl.class);
	private ReviewDAO reviewDAO = null;
	private BookDAO bookDAO = null;
	
	public ReviewServiceImpl() {
		reviewDAO = new ReviewDAOImpl();
		bookDAO = new BookDAOImpl();
	}

	public Review findByReview(Long clienteId, Long libroId, String locale) throws DataException {

		Connection con = null;
		Review review = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			review = reviewDAO.findByReview(con, clienteId, libroId, locale);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return review;

	}

	public Results<Review> findByUser(Long userId, int pos, int pageSize, String locale) throws DataException{

		Connection con = null;
		Results<Review> results = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = reviewDAO.findByUser(con, userId, locale, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}

	public Results<Review> findByReviewCriteria(ReviewCriteria reviewCriteria, int pos, int pageSize)
			throws DataException{

		Connection con = null;
		Results<Review> results = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = reviewDAO.findByReviewCriteria(con, reviewCriteria, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}


	public Results<Review> findByBook(Long bookId, int pos, int pageSize, String locale) throws DataException{

		Connection con = null;
		Results<Review> results = null;
		boolean commit = true;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = reviewDAO.findByBook(con, bookId, locale, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}


	public void create(Review review, String locale) throws DataException{

		Connection con = null;
		boolean commit = false;
		boolean flag = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			reviewDAO.create(con, review, locale);
			Book libro = bookDAO.findByBook(con, locale, review.getBookId());
			libro.setAverageRating(calcularMedia(reviewDAO.findByBook(con, review.getBookId(), locale, 1, Integer.MAX_VALUE).getPage()));
			flag = bookDAO.update(con, libro);
			if(flag) {
				commit = true;
			}
			
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}

	}

	public boolean delete(Long userId, Long bookId) throws DataException{

		Connection con = null;
		boolean id = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = reviewDAO.delete(con, userId, bookId);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}


	public boolean update(Review review) throws DataException{

		Connection con = null;
		boolean i = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			i = reviewDAO.update(con, review);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return i;
	}

	
	public Double calculateAverage(List<Review> reviews) throws DataException {
		
		double totalRatings = 0;
		int count = 0;
		double average = 0;
		
		for(int i = 0; i<reviews.size(); i++) {
			totalRatings += reviews.get(i).getRating();
			count++;
		}
		
		average = totalRatings/count;
		
		return average;
	}

}
