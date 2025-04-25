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
import com.thegoldenbook.dao.ProvinciaDAO;
import com.thegoldenbook.model.Provincia;
import com.thegoldenbook.util.JDBCUtils;

public class ProvinciaDAOImpl implements ProvinciaDAO{

	private static Logger logger = LogManager.getLogger(ProvinciaDAOImpl.class);

	public Provincia findById(Connection con, int id) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;
		Provincia p = null;

		try {
			StringBuilder query = new StringBuilder(" SELECT ID ,NOMBRE, PAIS_ID ")
					.append(" FROM PROVINCIA ")
					.append(" WHERE ID = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setInt(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				p = loadNext(rs);
			}

		} catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return p;
	}



	public List<Provincia> findAll(Connection con) throws DataException{

		List<Provincia>resultados = new ArrayList<Provincia>();;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT ID ,NOMBRE, PAIS_ID ")
					.append(" FROM PROVINCIA ");

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
	
	protected Provincia loadNext (ResultSet rs ) throws SQLException {

		int i = 1;
		Provincia p = new Provincia();

		p.setId(rs.getInt(i++));
		p.setNombre(rs.getString(i++));
		p.setPaisId(rs.getInt(i++));

		return p;
	}
}



