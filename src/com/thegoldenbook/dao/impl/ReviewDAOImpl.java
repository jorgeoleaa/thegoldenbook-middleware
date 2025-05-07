package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.ReviewDAO;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ReviewCriteria;
import com.thegoldenbook.util.JDBCUtils;
import com.thegoldenbook.util.SQLUtils;

public class ReviewDAOImpl implements ReviewDAO{

	private static Logger logger = LogManager.getLogger(ReviewDAOImpl.class);

	public ReviewDAOImpl() {

	}

	public Results<Review> findByReviewCriteria(Connection con, ReviewCriteria reviewCriteria, int pos, int pageSize ) throws DataException{

		Results<Review> results = new Results<Review>();
		List<String> conditions = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" select r.book_id, b.title, a.name, a.last_name, a.second_last_name, r.user_id, u.nickname, u.name, u.last_name, u.second_last_name, r.rating, r.subject, r.body, r.published_date, r.language_id, ll.name")
					.append(" from review r ")
					.append(" inner join book b on b.id = r.book_id ")
					.append(" inner join book_author ba on ba.book_id = b.id ")
					.append(" inner join author a on a.id = ba.author_id ")
					.append(" inner join user u on u.id = r.user_id ")
					.append(" inner join language l on l.id = r.language_id ")
					.append(" inner join language l2 on l2.locale = ? ")
					.append(" inner join language_language ll on ll.language_id1 = l.id and ll.language_id = l2.id ");


			if (reviewCriteria.getUserId() != null) { 
				conditions.add(" r.user_id = ? ");
			}

			if(reviewCriteria.getBookId() != null) {
				conditions.add(" r.book_id = ? ");
			}

			if(reviewCriteria.getStartDate()!= null) {
				conditions.add(" r.published_date >= ? ");
			}

			if(reviewCriteria.getEndDate() != null) {
				conditions.add(" r.published_date <= ? ");
			}

			if(reviewCriteria.getContent()!= null) {
				conditions.add(" r.subject like ? or r.body like ? ");
			}

			if (!conditions.isEmpty()) {
				query.append(" where ");
				query.append(String.join(" and ", conditions));
			}

			query.append(" order by ").append(reviewCriteria.getOrderBy()).append(reviewCriteria.getAscDesc() ? " asc " : " desc ");

			String sql = query.toString();

			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			pst.setString(i++, reviewCriteria.getLocale());
			
			if(reviewCriteria.getUserId() != null) {
				pst.setLong(i++, reviewCriteria.getUserId());
			}

			if (reviewCriteria.getBookId() != null) {
				pst.setLong(i++, reviewCriteria.getBookId());
			}

			if (reviewCriteria.getStartDate() != null) {
				pst.setDate(i++, new java.sql.Date(reviewCriteria.getStartDate().getTime()));
			}

			if (reviewCriteria.getEndDate() != null) {
				pst.setDate(i++, new java.sql.Date(reviewCriteria.getEndDate().getTime()));
			}


			if (reviewCriteria.getContent() != null) {
				pst.setString(i++, SQLUtils.envolverLike(reviewCriteria.getContent()));
				pst.setString(i++, SQLUtils.envolverLike(reviewCriteria.getContent()));

			}

			logger.info("Running query: "+query);
			rs = pst.executeQuery();

			int count = 0;

			if((pos>=1) && rs.absolute(pos)) {

				do {
					results.getPage().add(loadNext(rs));

				}while(count<pageSize && rs.next());

			}
			results.setTotal(JDBCUtils.getTotalRows(rs));


		}catch(SQLException e){
			logger.error("ReviewCriteria: "+reviewCriteria ,e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return results;
	}

	public Results<Review> findByUser(Connection con, Long userId, String locale, int pos, int pageSize) throws DataException{

		Results<Review> resultados = new Results<Review>();
		ResultSet rs = null;
		PreparedStatement pst = null;

		try {

			StringBuilder query = new StringBuilder(" select r.book_id, b.title, a.name, a.last_name, a.second_last_name, r.user_id, u.nickname, u.name, u.last_name, u.second_last_name, r.rating, r.subject, r.body, r.published_date, r.language_id, ll.name ")
					.append(" from review r ")
					.append(" inner join book b on b.id = r.book_id ")
					.append(" inner join book_author ba on ba.book_id = b.id ")
					.append(" inner join author a on a.id = ba.author_id ")
					.append(" inner join user u on u.id = r.user_id ")
					.append(" inner join language l on l.id = r.language_id ")
					.append(" inner join language_language ll on ll.language_id1 = l.id ")
					.append(" inner join language l2 on l2.id = ll.language_id ")
					.append(" where l2.locale = ? and u.id = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, userId);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs));
					count++;
				}while (count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		} catch(SQLException e) {
			logger.error("UserId: "+userId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}


	public Results<Review> findByBook(Connection con, Long bookId, String locale, int pos, int pageSize) throws DataException{

		Results<Review> resultados = new Results<Review>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" select r.book_id, b.title, a.name, a.last_name, a.second_last_name, r.user_id, u.nickname, u.name, u.last_name, u.second_last_name, r.rating, r.subject, r.body, r.published_date, r.language_id, ll.name ")
					.append(" from review r ")
					.append(" inner join book b on b.id = r.book_id ")
					.append(" inner join book_author ba on ba.book_id = b.id ")
					.append(" inner join author a on a.id = ba.author_id ")
					.append(" inner join user u on u.id = r.user_id ")
					.append(" inner join language l on l.id = r.language_id ")
					.append(" inner join language_language ll on ll.language_id1 = l.id ")
					.append(" inner join language l2 on l2.id = ll.language_id ")
					.append(" where l2.locale = ? and b.id = ?");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, bookId);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs));
					count++;
				}while (count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		} catch(SQLException e) {
			logger.error("BookId: "+bookId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}

	public boolean delete(Connection con, Long userId, Long bookId) throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement("delete from review where user_id = ? and book_id = ? ");

			int i = 1;
			pst.setLong(i++, userId);
			pst.setLong(i++, bookId);

			int deletedRows = pst.executeUpdate();

			if(deletedRows != 1) {
				return false;
			}

		} catch (SQLException e) {
			logger.error("UserId: "+userId, "BookId: "+bookId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public boolean update(Connection con, Review review) throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(" update review set rating = ?, subject = ?, body = ? "
					+ "where user_id = ? and book_id = ? ");

			int i = 1;

			pst.setDouble(i++, review.getRating());
			pst.setString(i++, review.getSubject());
			pst.setString(i++, review.getBody());
			pst.setLong(i++, review.getUserId());
			pst.setLong(i++, review.getBookId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;

			}

		} catch(SQLException e) {
			logger.error("Review: "+review, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(null, null);
		}
		return true;
	}

	public void create(Connection con, Review review) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement("insert into review (user_id, book_id, rating, subject, body, published_date) "
					+ " values(?,?,?,?,?,?)");

			int i = 1;
			pst.setLong(i++, review.getUserId());
			pst.setLong(i++, review.getBookId());
			pst.setDouble(i++, review.getRating());
			pst.setString(i++, review.getSubject());
			pst.setString(i++, review.getBody());
			pst.setTimestamp(i, new Timestamp(review.getPublicationDate().getTime()));

			pst.executeUpdate();


		} catch(SQLException e) {
			logger.error("Review: "+review, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}
	}

	protected Review loadNext (ResultSet rs) throws SQLException {

		Review review = new Review();
		int i = 1;

		review.setUserId(rs.getLong(i++));
		review.setNickname(rs.getString(i++));
		review.setUserName(rs.getString(i++));
		review.setUserLastName(rs.getString(i++));
		review.setUserSecondLastName(rs.getString(i++));
		review.setBookId(rs.getLong(i++));
		review.setBookName(rs.getString(i++));
		review.setAuthorId(rs.getLong(i++));
		review.setAuthorName(rs.getString(i++));
		review.setAuthorLastName(rs.getString(i++));
		review.setAuthorSecondLastName(rs.getString(i++));
		review.setRating(rs.getDouble(i++));
		review.setSubject(rs.getString(i++));
		review.setBody(rs.getString(i++));
		review.setPublicationDate(rs.getTimestamp(i++));

		return review;
	}




	public Review findByReview(Connection con, Long userId, Long bookId, String locale) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Review v = null;
		try {
			StringBuilder query = new StringBuilder(" select r.book_id, b.title, a.name, a.last_name, a.second_last_name, r.user_id, u.nickname, u.name, u.last_name, u.second_last_name, r.rating, r.subject, r.body, r.published_date, r.language_id, ll.name ")
					.append(" from review r ")
					.append(" inner join book b on b.id = r.book_id ")
					.append(" inner join book_author ba on ba.book_id = b.id ")
					.append(" inner join author a on a.id = ba.author_id ")
					.append(" inner join user u on u.id = r.user_id ")
					.append(" inner join language l on l.id = r.language_id ")
					.append(" inner join language_language ll on ll.language_id1 = l.id ")
					.append(" inner join language l2 on l2.id = ll.language_id ")
					.append(" where l2.locale = ? and b.id = ? and u.id = ? ");


			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, userId);
			pst.setLong(i++, bookId);

			rs = pst.executeQuery();

			if(rs.next()) {
				v = loadNext(rs);
			}

		}catch(SQLException e) {
			logger.error("UserId: "+userId, "BookId: "+bookId, "Locale: "+locale, e); 
			throw new DataException(e);
		}
		return v;
	}
}
