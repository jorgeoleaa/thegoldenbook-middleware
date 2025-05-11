package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.AuthorDAO;
import com.thegoldenbook.dao.BookDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LiteraryGenreDAO;
import com.thegoldenbook.dao.SubjectDAO;
import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.LiteraryGenre;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.service.BookCriteria;
import com.thegoldenbook.util.JDBCUtils;
import com.thegoldenbook.util.SQLUtils;

public class BookDAOImpl implements BookDAO {

	private static Logger logger = LogManager.getLogger(BookDAOImpl.class);
	private AuthorDAO authorDAO = null;
	private SubjectDAO subjectDAO = null;
	private LiteraryGenreDAO literaryGenreDAO = null;

	public BookDAOImpl() {
		authorDAO = new AuthorDAOImpl();
		subjectDAO = new SubjectDAOImpl();
		literaryGenreDAO = new LiteraryGenreDAOImpl();
	}

	public Book findByBook(Connection con, String locale, Long id) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Book book = null;

		try {
			StringBuilder query = new StringBuilder("select b.id, b.isbn, b.title, b.synopsis, b.publication_date, b.average_rating, b.stock, b.price, b.language_id, ll.name, f.id, fl.name, rag.id, ragl.name")
					.append(" from book b ")
					.append(" inner join language l on l.id = b.language_id ")
					.append(" inner join language l2 on l2.locale = ? ")
					.append(" inner join language_language ll on ll.language_id1 = l.id and ll.language_id = l2.id ")
					.append(" inner join format f on b.format_id = f.id ")
					.append(" inner join format_language fl on fl.format_id = f.id and fl.language_id = l2.id ")
					.append(" inner join reading_age_group rag on b.reading_age_group_id = rag.id ")
					.append(" inner join reading_age_group_language ragl on ragl.reading_age_group_id = rag.id and ragl.language_id = l2.id ")
					.append(" where b.id = ? ");
					


			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, id);

			rs = pst.executeQuery();
			if (rs.next()) {
				book = loadNext(rs, locale, con);
			}

		} catch (SQLException e) {
			logger.error("ID:" + id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pst, rs);
		}
		return book;
	}

	public Results<Book> findByCriteria(Connection con, BookCriteria bookCriteria, int pos, int pageSize) throws DataException {

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Results<Book> results = new Results<Book>();
		List<String> conditions = new ArrayList<String>();

		try {

			StringBuilder query = new StringBuilder("select b.id, b.isbn, b.title, b.synopsis, b.publication_date, b.average_rating, b.stock, b.price, b.language_id, ll.name, f.id, fl.name, rag.id, ragl.name")
					.append(" from book b ")
					.append(" inner join language l on l.id = b.language_id ")
					.append(" inner join language l2 on l2.locale = ? ")
					.append(" inner join language_language ll on ll.language_id1 = l.id and ll.language_id = l2.id ")
					.append(" inner join format f on b.format_id = f.id ")
					.append(" inner join format_language fl on fl.format_id = f.id and fl.language_id = l2.id ")
					.append(" inner join reading_age_group rag on b.reading_age_group_id = rag.id ")
					.append(" inner join reading_age_group_language ragl on ragl.reading_age_group_id = rag.id and ragl.language_id = l2.id ");

			
			
			if (bookCriteria.getId() != null) {
				conditions.add(" b.id = ? ");
			} else if (bookCriteria.getIsbn() != null) {
				conditions.add(" b.isbn like ? ");
			}

			else {
				if (bookCriteria.getTitle() != null) {
					conditions.add(" b.title like ? ");
				}

				if (bookCriteria.getStartDate() != null) {
					conditions.add(" b.publication_date >= ? ");
				}

				if (bookCriteria.getEndDate() != null) {
					conditions.add(" b.publication_date <= ? ");
				}

				if (bookCriteria.getMinUnits() != null) {
					conditions.add(" b.stock >= ? ");
				}

				if (bookCriteria.getMaxUnits() != null) {
					conditions.add(" b.stock <= ? ");
				}

				if (bookCriteria.getMinPrice() != null) {
					conditions.add(" b.price >= ? ");
				}

				if (bookCriteria.getMaxPrice() != null) {
					conditions.add(" b.price <= ? ");
				}

				if (bookCriteria.getReadingAgeGroupId() != null) {
					conditions.add(" b.reading_age_group_id = ? ");
				}

				if(bookCriteria.getLanguageId() != null) {
					conditions.add(" b.language_id = ? ");
				}

				if(bookCriteria.getFormatId() != null) {
					conditions.add(" b.format_id = ? ");
				}
			}
			if (!conditions.isEmpty()) {

				query.append(" where ");
				query.append(String.join(" and ", conditions));
			}

			query.append(" order by ").append(bookCriteria.getOrderBy()).append(bookCriteria.getAscDesc() ? " asc " : " desc ");


			String sql = query.toString();

			preparedStatement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, bookCriteria.getLocale());

			if (bookCriteria.getId() != null) {
				preparedStatement.setLong(i++, bookCriteria.getId());
			}

			else if (bookCriteria.getIsbn() != null) {
				preparedStatement.setString(i++,  SQLUtils.wrapForLike(bookCriteria.getIsbn()));
			} else {

				if (bookCriteria.getTitle() != null) {
					preparedStatement.setString(i++, SQLUtils.wrapForLike(bookCriteria.getTitle()));
				}

				if (bookCriteria.getStartDate() != null) {
					preparedStatement.setDate(i++, new java.sql.Date(bookCriteria.getStartDate().getTime()));
				}

				if (bookCriteria.getEndDate() != null) {
					preparedStatement.setDate(i++, new java.sql.Date(bookCriteria.getEndDate().getTime()));
				}

				if(bookCriteria.getMinUnits() != null) {
					preparedStatement.setInt(i++, bookCriteria.getMinUnits());
				}

				if(bookCriteria.getMaxUnits() != null) {
					preparedStatement.setInt(i++, bookCriteria.getMaxUnits());
				}

				if (bookCriteria.getMinPrice() != null) {
					preparedStatement.setDouble(i++, bookCriteria.getMinPrice());
				}

				if (bookCriteria.getMaxPrice() != null) {
					preparedStatement.setDouble(i++, bookCriteria.getMaxPrice());
				}

				if (bookCriteria.getReadingAgeGroupId() != null) {
					preparedStatement.setInt(i++, bookCriteria.getReadingAgeGroupId());
				}

				if(bookCriteria.getLanguageId() != null) {
					preparedStatement.setInt(i++, bookCriteria.getLanguageId());
				}

				if(bookCriteria.getFormatId() != null) {
					preparedStatement.setInt(i++, bookCriteria.getFormatId());
				}
			}

			rs = preparedStatement.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					results.getPage().add(loadNext(rs, bookCriteria.getLocale(), con));
					count++;
				}while (count<pageSize && rs.next());
			}
			results.setTotal(JDBCUtils.getTotalRows(rs));

		} catch (SQLException e) {
			logger.error("BookCriteria: " + bookCriteria, e);
			throw new DataException(e);

		} finally {
			JDBCUtils.close(preparedStatement, rs);
		}

		return results;
	}

	public Long create(Connection con, String locale, Book book) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			preparedStatement = con.prepareStatement(
					" insert into book(isbn, title, synopsis, publication_date, average_rating, stock, price, reading_age_group_id, language_id, format_id)"
							+ " values(?,?,?,?,?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			JDBCUtils.setNullable(preparedStatement, i++, book.getIsbn());
			preparedStatement.setString(i++, book.getTitle());
			preparedStatement.setString(i++, book.getSynopsis());
			JDBCUtils.setNullable(preparedStatement, i++, new java.sql.Date(book.getPublicationDate().getTime()));
			JDBCUtils.setNullable(preparedStatement, i++, book.getAverageRating());
			preparedStatement.setInt(i++, book.getStock());
			preparedStatement.setDouble(i++, book.getPrice());
			preparedStatement.setInt(i++, book.getReadingAgeGropuId());
			preparedStatement.setInt(i++, book.getLanguageId());
			preparedStatement.setInt(i++, book.getFormatId());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows != 1) {
			}

			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				Long id = rs.getLong(1);
				book.setId(id);

				List<Long> ids = new ArrayList<Long>();
				for(Author a : book.getAuthors()) {
					ids.add(a.getId());
				}
				assignAuthors(con, id, ids);

				List<Integer> idsT = new ArrayList<Integer>();
				for (Subject subject : book.getSubjects()) {
					idsT.add(subject.getId());
				}
				assignSubjects(con, locale, id, idsT);

				return id;
			}

		} catch (SQLException e) {
			logger.error("Books: " + book, e);
			throw new DataException(e);

		} finally {
			JDBCUtils.close(preparedStatement, rs);
		}

		return null;
	}

	
	public boolean update(Connection con, Book l) throws DataException {

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(
					"update book set isbn = ?, title = ?, synopsis = ?, publication_date = ?, average_media = ?, stock = ?, price = ?, literary_genre_id = ?, reading_age_group = ?, language_id = ?, format_id = ? "
							+ " where id = ? ");

			int i = 1;

			JDBCUtils.setNullable(pst, i++, l.getIsbn());
			pst.setString(i++, l.getTitle());
			pst.setString(i++, l.getSynopsis());
			JDBCUtils.setNullable(pst, i++, new java.sql.Date(l.getPublicationDate().getTime()));
			JDBCUtils.setNullable(pst, i++, l.getAverageRating());
			pst.setInt(i++, l.getStock());
			pst.setDouble(i++, l.getPrice());
			pst.setInt(i++, l.getReadingAgeGropuId());
			pst.setInt(i++, l.getLanguageId());
			pst.setInt(i++, l.getFormatId());
			pst.setLong(i++, l.getId());
			int updatedRows = pst.executeUpdate();

			if (updatedRows != 1) {
				return false;
			}

		} catch (SQLException e) {
			logger.error("Book: " + l, e);
			throw new DataException(e);

		} finally {
			JDBCUtils.close(pst);
		}

		return true;
	}

	public void assignAuthors(Connection con, Long bookId, List<Long>authorsId) throws DataException {

		PreparedStatement pst = null;
		List<Author> authorsBook = authorDAO.findByBook(con, bookId);
		
		
		if(authorsBook.isEmpty()) {

		}else {
			try {
				pst = con.prepareStatement(" delete from book_author where book_id = ? ");

				int i = 1;
				pst.setLong(i++, bookId);

				pst.executeUpdate();

			}catch(SQLException e) {
				logger.error("BookId: " + bookId , e);
				throw new DataException(e);
			}finally {
				JDBCUtils.close(pst);
			}
		}


		try {
			StringBuilder query = new StringBuilder (" insert into book_author (book_id, author_id)");
			query.append("values");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", authorsId.size());
			
			pst = con.prepareStatement(query.toString());
			
			int i = 1;
			for(Long id : authorsId) {
				pst.setLong(i++, bookId);
				pst.setLong(i++, id);
			}
			

			pst.executeUpdate(); 

		}catch (SQLException e) {
			logger.error("BookId: " + bookId, " AuthorsId: "+authorsId  , e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pst);
		}



	}

	public void assignSubjects(Connection con, String locale, Long bookId, List<Integer>subjectsId) throws DataException {

		PreparedStatement pst = null;
		List<Subject> subjects = subjectDAO.findByBook(con, locale, bookId);

		if(subjects.isEmpty()) {

		}else {
			try {

				pst = con.prepareStatement(" delete from book_subject where book_id = ? ");

				int i = 1;
				pst.setLong(i++, bookId);

				pst.executeUpdate();

			}catch(SQLException e) {
				logger.error("LibroID: "+bookId, e);
				throw new DataException(e);
			}finally {
				JDBCUtils.close(pst);
			}
		}

		

	}
	
	@Override
	public void assignLiteraryGenres(Connection con, String locale, Long bookId, List<Integer> literaryGenresId)
			throws DataException {
		
		PreparedStatement pst = null;
		List<LiteraryGenre> literaryGenres = literaryGenreDAO.findByBook(con, locale, bookId);
		
		if(literaryGenres.isEmpty()) {
			
		}else {
			
			try {

				pst = con.prepareStatement(" delete from book_literary_genre where book_id = ? ");

				int i = 1;
				pst.setLong(i++, bookId);

				pst.executeUpdate();

			}catch(SQLException e) {
				logger.error("LibroID: "+bookId, e);
				throw new DataException(e);
			}finally {
				JDBCUtils.close(pst);
			}
		}
		
		try {
			StringBuilder query = new StringBuilder( "insert into book_literary_genre (book_id, literary_genre_id)");
			query.append("values");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", literaryGenresId.size());
			
			pst = con.prepareStatement(query.toString());
			
			int i = 1;
			for(Integer id : literaryGenresId) {
				pst.setLong(i++, bookId);
				pst.setInt(i++, id);
			}
			

			pst.executeUpdate();

		}catch(SQLException e) {
			logger.error("BookId: "+bookId," LiteraryGenresId: "+literaryGenresId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		
	}
	
	protected Book loadNext(ResultSet rs, String locale, Connection con) throws SQLException, DataException {
		
		int i;
		
		Book book = new Book();
		
		i = 1;
		
		book = new Book();
		book.setId(rs.getLong(i++));
		book.setIsbn(rs.getString(i++));
		book.setTitle(rs.getString(i++));
		book.setSynopsis(rs.getString(i++));
		book.setPublicationDate(rs.getDate(i++));
		book.setAverageRating(JDBCUtils.getNullableDouble(rs, i++));
		book.setStock(rs.getInt(i++));
		book.setPrice(rs.getDouble(i++));
		book.setReadingAgeGropuId(rs.getInt(i++));
		book.setReadingAgeGroupName(rs.getString(i++));
		book.setFormatId(rs.getInt(i++));
		book.setFormatName(rs.getString(i++));
		book.setLanguageId(rs.getInt(i++));
		book.setLanguageName(rs.getString(i++));
		book.setAuthors(authorDAO.findByBook(con, book.getId()));
		book.setSubjects(subjectDAO.findByBook(con, locale, book.getId()));
		book.setLiteraryGenres(literaryGenreDAO.findByBook(con, locale, book.getId()));


		return book;
	}
}
