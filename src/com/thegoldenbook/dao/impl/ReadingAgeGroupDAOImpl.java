package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.ReadingAgeGroupDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.ReadingAgeGroup;
import com.thegoldenbook.util.JDBCUtils;

public class ReadingAgeGroupDAOImpl implements ReadingAgeGroupDAO{

	private static Logger logger = LogManager.getLogger(ReadingAgeGroupDAOImpl.class);
	
	public ReadingAgeGroupDAOImpl() {
		
	}
	
	public List<ReadingAgeGroup> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ReadingAgeGroup> results = new ArrayList<ReadingAgeGroup>();
		
		try {
			
			StringBuilder query = new StringBuilder (" select rag.id, cei.name, rag.age")
					.append(" from reading_age_group rag ")
					.append(" inner join reading_age_group_language ragl ON ragl.reading_age_group_id = rag.ID")
					.append(" inner join language l on l.ID = ragl.language_id")
					.append(" where l.locale = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
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
	
	
	protected ReadingAgeGroup loadNext (ResultSet rs) throws SQLException{
		
		ReadingAgeGroup cd = new ReadingAgeGroup();
		int i = 1;
		
		cd.setId(rs.getInt(i++));
		cd.setName(rs.getString(i++));
		cd.setAge(rs.getString(i++));
		
		return cd;
	}
}
