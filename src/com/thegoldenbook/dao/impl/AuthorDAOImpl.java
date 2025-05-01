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

public class AutorDAOImpl implements AuthorDAO{

	private static Logger logger = LogManager.getLogger(AutorDAOImpl.class);

	public AutorDAOImpl() {

	}

	public Results<Author> findAll(Connection con, int pos, int pageSize) throws Exception {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Results<Author> resultados = new Results<Author>();

		try {

			StringBuilder query = new StringBuilder(" SELECT A.ID, A.NOMBRE, A.APELLIDO1, A.APELLIDO2, A.FECHA_NACIMIENTO ")
					.append(" FROM AUTOR A ")
					.append(" ORDER BY A.NOMBRE ASC");

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

	public Author findByAutor(Connection con, Long id) throws DataException{

		ResultSet rs = null;
		PreparedStatement pst = null;
		Author a = null;

		try {
			StringBuilder query = new StringBuilder("SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, FECHA_NACIMIENTO")
					.append(" FROM AUTOR ")
					.append(" WHERE ID = ? ");

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

	public List<Author> findByLibro(Connection con, Long libroId) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Author> resultados = new ArrayList<Author>();

		try {

			StringBuilder query = new StringBuilder(" SELECT A.ID, A.NOMBRE, A.APELLIDO1, A.APELLIDO2, A.FECHA_NACIMIENTO ")
					.append(" FROM AUTOR A ")
					.append(" INNER JOIN LIBRO_AUTOR LA ON LA.AUTOR_ID = A.ID ")
					.append(" INNER JOIN LIBRO L ON L.ID = LA.LIBRO_ID")
					.append(" WHERE L.ID = ? ")
					.append(" ORDER BY A.NOMBRE ASC");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, libroId);

			rs = pst.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}

		}catch(SQLException e) {
			logger.error("LibroID: "+libroId, e);
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
			pst = con.prepareStatement(" INSERT INTO AUTOR (NOMBRE, APELLIDO1, APELLIDO2, FECHA_NACIMIENTO)"
					+ " VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			pst.setString(i++, a.getNombre());
			pst.setString(i++, a.getApellido1());
			pst.setString(i++, a.getApellido2());
			pst.setDate(i++, new java.sql.Date(a.getFechaNacimiento().getTime()));

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
			logger.error("Autor: "+a, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return null;
	}

	public boolean update(Connection con, Author a) throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(" UPDATE AUTOR SET NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, FECHA_NACIMIENTO = ? "
					+ " WHERE ID = ? ");

			int i = 1;
			pst.setString(i++, a.getNombre());
			pst.setString(i++, a.getApellido1());
			pst.setString(i++, a.getApellido2());
			pst.setDate(i++,new java.sql.Date(a.getFechaNacimiento().getTime()));
			pst.setLong(i++, a.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;
			}

		}catch(SQLException e) {
			logger.error("Autor: "+a, e);
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
		a.setNombre(rs.getString(i++));
		a.setApellido1(rs.getString(i++));
		a.setApellido2(rs.getString(i++));
		a.setFechaNacimiento(rs.getDate(i++));

		return a;
	}
}
