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
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.util.JDBCUtils;

public class AuthorDAOImpl implements AuthorDAO{

	private static Logger logger = LogManager.getLogger(AuthorDAOImpl.class);

	public AuthorDAOImpl() {

	}

	public Results<Author> findAll(Connection con, int pos, int pageSize) throws Exception {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Results<Author> resultados = new Results<Author>();

		try {

			StringBuilder query = new StringBuilder(" select a.id, a.name, a.last_name, a.second_last_name, a.date_of_birth ")
					.append(" from author a ")
					.append(" order by a.name asc");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs));
					count++;
				}while (count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		}catch(SQLException e) {
			logger.error(e);
			throw new DataException (e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}

	public Author findByAuthor(Connection con, Long id) throws DataException{

		ResultSet rs = null;
		PreparedStatement pst = null;
		Author a = null;

		try {
			StringBuilder query = new StringBuilder("select id, name, last_name, second_last_name, date_of_birth")
					.append(" from author ")
					.append(" where id = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				a = loadNext(rs);
			}

		} catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return a;

	}

	public List<Author> findByBook(Connection con, Long bookId) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Author> resultados = new ArrayList<Author>();

		try {

			StringBuilder query = new StringBuilder(" select a.id, a.name, a.last_name, a.second_last_name, a.date_of_birth")
					.append(" from author a ")
					.append(" inner join book_author ba on ba.author_id = a.id")
					.append(" inner join book b ON b.id = ba.book_id")
					.append(" where b.id = ? ")
					.append(" order by a.name asc");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, bookId);

			rs = pst.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}

		}catch(SQLException e) {
			logger.error("Book id: "+bookId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}

	public Long create(Connection con, Author a) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(" insert into author (name, last_name, second_last_name, date_of_birth)"
					+ " values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			pst.setString(i++, a.getName());
			pst.setString(i++, a.getLastName());
			pst.setString(i++, a.getSecondLastName());
			pst.setDate(i++, new java.sql.Date(a.getDateOfBirth().getTime()));

			int insertedRows = pst.executeUpdate();

			if(insertedRows == 0) {
				throw new SQLException();
			}

			rs = pst.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				a.setId(id);
				return id;
			}


		} catch(SQLException e) {
			logger.error("Author: "+a, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return null;
	}

	public boolean update(Connection con, Author a) throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(" update author set name = ?, last_name = ?, second_last_name = ?, date_of_birth = ? "
					+ " where id = ? ");

			int i = 1;
			pst.setString(i++, a.getName());
			pst.setString(i++, a.getLastName());
			pst.setString(i++, a.getSecondLastName());
			pst.setDate(i++,new java.sql.Date(a.getDateOfBirth().getTime()));
			pst.setLong(i++, a.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;
			}

		}catch(SQLException e) {
			logger.error("Author: "+a, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}

		return true;
	}

	protected Author loadNext (ResultSet rs) throws SQLException{

		Author a = new Author();
		int i = 1;

		a.setId(rs.getLong(i++));
		a.setName(rs.getString(i++));
		a.setLastName(rs.getString(i++));
		a.setSecondLastName(rs.getString(i++));
		a.setDateOfBirth(rs.getDate(i++));

		return a;
	}
}
