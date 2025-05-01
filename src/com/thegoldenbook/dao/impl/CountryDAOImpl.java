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
import com.thegoldenbook.dao.CountryDAO;
import com.thegoldenbook.model.Country;
import com.thegoldenbook.util.JDBCUtils;

public class CountryDAOImpl implements CountryDAO{

	private static Logger logger = LogManager.getLogger(CountryDAOImpl.class);

	public CountryDAOImpl() {

	}

	public List<Country> findAll(Connection con, String locale) throws DataException{

		List<Country> results = new ArrayList<Country>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			
			StringBuilder query = new StringBuilder(" select c.id, cl.name, c.iso1, c.iso2, c.phone_code ")
					.append(" from country c ")
					.append(" inner join country_language cl on cl.country_id = c.id ")
					.append("inner join language l on l.id = cl.language_id")
					.append(" where l.locale = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);

			rs = pst.executeQuery();

			while(rs.next()) {
				results.add(loadNext(rs));
			}

		}catch(SQLException e) {
			logger.error("Locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return results;
	}

	public Country findById(Connection con, int id, String locale) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Country p = null;
		try { 
			StringBuilder query = new StringBuilder(" select c.id, cl.name, c.iso1, c.iso2, c.phone_code ")
					.append(" from country c ")
					.append(" inner join country_language cl on cl.country_id = c.id ")
					.append(" inner join language l on l.id = cl.language_id ")
					.append(" where l.locale = ? and c.id = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();

			if(rs.next()) {
				p = loadNext(rs);
			}
		}catch(SQLException e) {
			logger.error("ID: "+id+" locale: "+locale, e);
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
		p.setName(rs.getString(i++));
		p.setIso1(rs.getString(i++));
		p.setIso2(rs.getString(i++));
		p.setPhoneCode(rs.getString(i++));

		return p;
	}
}
