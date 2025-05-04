package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.SubjectDAO;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.util.JDBCUtils;

public class TematicaDAOImpl implements SubjectDAO{
	
	private static Logger logger = LogManager.getLogger(TematicaDAOImpl.class);
	
	public TematicaDAOImpl() {
		
	}
	
	public List<Subject> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> resultados = new ArrayList<Subject>();
		
		try {
			
			StringBuilder query = new StringBuilder (" SELECT T.ID, TI.NOMBRE ")
					.append(" FROM TEMATICA T ")
					.append(" INNER JOIN TEMATICA_IDIOMA TI ON TI.TEMATICA_ID = T.ID")
					.append(" INNER JOIN IDIOMA I ON I.ID = TI.IDIOMA_ID")
					.append(" WHERE I.LOCALE = ?");
			 
			pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
		
	}

	
	public List<Subject> findByLibro(Connection con, String locale,  Long libroId) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> resultados = new ArrayList<Subject>();
		
		try {
			StringBuilder query = new StringBuilder("SELECT T.ID, TI.NOMBRE ")
					.append(" FROM TEMATICA T ")
					.append(" INNER JOIN TEMATICA_IDIOMA TI ON TI.TEMATICA_ID = T.ID ")
					.append(" INNER JOIN IDIOMA I ON I.ID = TI.IDIOMA_ID ")
					.append(" INNER JOIN LIBRO_TEMATICA LT ON LT.TEMATICA_ID = T.ID ")
					.append(" INNER JOIN LIBRO L ON L.ID = LT.LIBRO_ID")
					.append(" WHERE I.LOCALE = ? AND L.ID = ? ");

			
			pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
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
	
	
	public Subject findById(Connection con, int id) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Subject t = null;
		
		try {
			
			StringBuilder query = new StringBuilder("SELECT ID, NOMBRE ")
					.append(" FROM TEMATICA ")
					.append(" WHERE ID = ? ");
			 
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				t = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return t;	
	}
	
	protected Subject loadNext(ResultSet rs) throws SQLException{
		
		Subject t = new Subject();
		int i = 1;
		
		t.setId(rs.getInt(i++));
		t.setNombre(rs.getString(i++));  
		
		return t;
	}


	
}
