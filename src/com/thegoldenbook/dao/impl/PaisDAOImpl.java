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
import com.thegoldenbook.dao.PaisDAO;
import com.thegoldenbook.model.Country;
import com.thegoldenbook.util.JDBCUtils;

public class PaisDAOImpl implements PaisDAO{

	private static Logger logger = LogManager.getLogger(PaisDAOImpl.class);

	public PaisDAOImpl() {

	}

	public List<Country> findAll(Connection con) throws DataException{

		List<Country>resultados = new ArrayList<Country>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID ,NOMBRE, ISO1, ISO2, PHONE_CODIGO ")
					.append(" FROM PAIS ").append(" ORDER BY NOMBRE ASC");

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

	public Country findById(Connection con, int id) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Country p = null;
		try { 
			StringBuilder query = new StringBuilder("SELECT ID, NOMBRE, ISO1, ISO2, PHONE_CODIGO ")
					.append(" FROM PAIS ")
					.append(" WHERE ID = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();

			if(rs.next()) {
				p = loadNext(rs);
			}
		}catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return p;
	}
	protected Country loadNext (ResultSet rs) throws SQLException{

		int i = 1;

		Country p = new Country();

		p.setId(rs.getInt(i++));
		p.setNombre(rs.getString(i++));
		p.setIso1(rs.getString(i++));
		p.setIso2(rs.getString(i++));
		p.setPhoneCodigo(rs.getString(i++));


		return p;
	}
}
