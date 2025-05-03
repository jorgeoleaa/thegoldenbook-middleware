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
import com.thegoldenbook.dao.LocalityDAO;
import com.thegoldenbook.model.Locality;
import com.thegoldenbook.util.JDBCUtils;

public class LocalidadDAOImpl implements LocalityDAO {
	
	private static Logger logger = LogManager.getLogger(LocalidadDAOImpl.class);
	
	public LocalidadDAOImpl() {
		
	}
	
	public Locality findById(Connection con, int id) throws DataException{
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		Locality l = null;
		
		try {
			StringBuilder query = new StringBuilder ("SELECT ID, NOMBRE, CODIGO_POSTAL, PROVINCIA_ID ")
					.append(" FROM LOCALIDAD ").append(" WHERE ID = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				l = loadNext(rs);
			}
			
		} catch (SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return l;
	}
	
	public Locality findByCodigoPostal (Connection con, int codigoPostal) throws DataException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Locality l = null;
		
		try {
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, CODIGO_POSTAL, PROVINCIA_ID ")
					.append(" FROM LOCALIDAD ")
					.append(" WHERE CODIGO_POSTAL = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setInt(i++, codigoPostal);
		
			rs = pst.executeQuery();
			
			if(rs.next()) {
				l = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error("Codigo postal: "+codigoPostal, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return l;
	}
	
	
	public List<Locality> findAll(Connection con) throws DataException {
		
		List<Locality> resultados = new ArrayList<Locality>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE, CODIGO_POSTAL, PROVINCIA_ID FROM LOCALIDAD");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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
	
	protected Locality loadNext (ResultSet rs) throws SQLException{
		
		int i = 1;
		
		Locality l = new Locality();
		
		l.setId(rs.getInt(i++));
		l.setNombre(rs.getString(i++));
		l.setCodigoPostal(rs.getInt(i++));
		l.setProvinciaId(rs.getInt(i++));
	
		
		return l;
	}

}
