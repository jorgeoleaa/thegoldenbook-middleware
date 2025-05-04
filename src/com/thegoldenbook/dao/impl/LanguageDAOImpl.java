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
import com.thegoldenbook.dao.LanguageDAO;
import com.thegoldenbook.model.Language;
import com.thegoldenbook.util.JDBCUtils;

public class LanguageDAOImpl implements LanguageDAO{

	private static Logger logger = LogManager.getLogger(LanguageDAOImpl.class);
	
	public LanguageDAOImpl() {
		
	}
	
	public List<Language> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Language> resultados = new ArrayList<Language>();
		
		try {
			
			StringBuilder query = new StringBuilder (" select l2.id, ll.name, l2.iso639, l2.locale  ")
					.append(" from language l ")
					.append(" inner join language_language ll on ll.language_id = l.id ")
					.append(" inner join language l2 on l2.id = ll.language_id1 ")
					.append(" where l.locale = ? ");
					
					
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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
	
	@Override
	public Language findById(Connection con, String locale, int id) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Language idioma = null;
		
		try {
			StringBuilder query = new StringBuilder (" select l2.id, ll.name, l2.iso639, l2.locale ")
					.append(" from language l ")
					.append(" inner join language_language ll on ll.language_id = l.id ")
					.append(" inner join language l2 on l2.id = ll.language_id1 ")
					.append(" where l.locale = ? and l2.id = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				idioma = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return idioma;
	}
	
	protected Language loadNext(ResultSet rs) throws SQLException{
		
		Language id = new Language();
		
		int i = 1;
		id.setId(rs.getInt(i++));
		id.setName(rs.getString(i++));
		id.setIso639(rs.getString(i++));
		id.setLocale(rs.getString(i++));
		
		return id;
		
	}

	

	
}
