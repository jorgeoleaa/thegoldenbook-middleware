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
import com.thegoldenbook.dao.FormatDAO;
import com.thegoldenbook.model.Format;
import com.thegoldenbook.util.JDBCUtils;

public class FormatDAOImpl implements FormatDAO{

	private static Logger logger = LogManager.getLogger(SubjectDAOImpl.class);
	
	public FormatDAOImpl() {
		
	}
	
	public List<Format> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Format> results = new ArrayList<Format>();
		
		try {
			
			StringBuilder query = new StringBuilder (" select f.id, fl.name ")
					.append(" from format f ")
					.append(" inner join format_language fl on fl.format_id = f.id ")
					.append(" inner join language l on l.id = fl.language_id ")
					.append(" where l.locale = ? ");
			
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
	
	protected Format loadNext (ResultSet rs) throws SQLException{
		
		Format f = new Format();
		int i = 1;
		
		f.setId(rs.getInt(i++));
		f.setName(rs.getString(i++));
		
		return f;
	}

	
}
