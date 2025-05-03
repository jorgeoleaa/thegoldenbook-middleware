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

public class LocalityDAOImpl implements LocalityDAO {
	
	private static Logger logger = LogManager.getLogger(LocalityDAOImpl.class);
	
	public LocalityDAOImpl() {
		
	}
	
	public Locality findById(Connection con, int id, String locale) throws DataException{
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		Locality l = null;
		
		try {
			StringBuilder query = new StringBuilder (" select lo.id, ll.name, lo.postal_code, lo.region_id ")
					.append(" from locality lo ")
					.append(" inner join locality_language ll on ll.locality_id = lo.id ")
					.append(" inner join language l on l.id = ll.language_id ")
					.append(" where l.locale = ? and where lo.id = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				l = loadNext(rs);
			}
			
		} catch (SQLException e) {
			logger.error("ID: "+id+ " Locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return l;
	}
	
	public Locality findByPostalCode (Connection con, int postalCode, String locale) throws DataException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Locality l = null;
		
		try {
			StringBuilder query = new StringBuilder (" select lo.id, ll.name, lo.postal_code, lo.region_id ")
					.append(" from locality lo ")
					.append(" inner join locality_language ll on ll.locality_id = lo.id ")
					.append(" inner join language l on l.id = ll.language_id ")
					.append(" where l.locale = ? and where lo.postal_code = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, postalCode);
		
			rs = pst.executeQuery();
			
			if(rs.next()) {
				l = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error("Codigo postal: "+postalCode+" locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return l;
	}
	
	
	public List<Locality> findAll(Connection con, String locale) throws DataException {
		
		List<Locality> results = new ArrayList<Locality>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder (" select lo.id, ll.name, lo.postal_code, lo.region_id ")
					.append(" from locality lo ")
					.append(" inner join locality_language ll on ll.locality_id = lo.id ")
					.append(" inner join language l on l.id = ll.language_id ")
					.append(" where l.locale = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				results.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return results;
	}
	
	protected Locality loadNext (ResultSet rs) throws SQLException{
		
		int i = 1;
		
		Locality l = new Locality();
		
		l.setId(rs.getInt(i++));
		l.setName(rs.getString(i++));
		l.setPostalCode(rs.getInt(i++));
		l.setRegionId(rs.getInt(i++));
		
		return l;
	}

}
