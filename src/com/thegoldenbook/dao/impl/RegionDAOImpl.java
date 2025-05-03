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
import com.thegoldenbook.dao.RegionDAO;
import com.thegoldenbook.model.Region;
import com.thegoldenbook.util.JDBCUtils;

public class RegionDAOImpl implements RegionDAO{

	private static Logger logger = LogManager.getLogger(RegionDAOImpl.class);

	public Region findById(Connection con, int id, String locale) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;
		Region p = null;

		try {
			StringBuilder query = new StringBuilder(" select r.id, rl.nmae, r.country_id")
					.append(" from region r ")
					.append(" inner join region_language rl on rl.region_id = r.id ")
					.append(" inner join language l on l.id = rl.language_id ")
					.append(" where l.locale = ? and r.id = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				p = loadNext(rs);
			}

		} catch(SQLException e) {
			logger.error("ID: "+id+" locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return p;
	}



	public List<Region> findAll(Connection con, String locale) throws DataException{

		List<Region>resultados = new ArrayList<Region>();;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" select r.id, rl.name, r.country_id ")
					.append(" from region r ")
					.append(" inner join region_language rl on rl.region_id = r.id ")
					.append(" inner join language l on l.id = rl.language_id ")
					.append(" where l.locale = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);

			rs = pst.executeQuery();

			while(rs.next()) {
				resultados.add(loadNext(rs));
			}

		}catch(SQLException e) {
			logger.error("Locale: "+locale ,e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}
	
	protected Region loadNext (ResultSet rs) throws SQLException {

		int i = 1;
		Region p = new Region();

		p.setId(rs.getInt(i++));
		p.setName(rs.getString(i++));
		p.setCountryId(rs.getInt(i++));

		return p;
	}
}



