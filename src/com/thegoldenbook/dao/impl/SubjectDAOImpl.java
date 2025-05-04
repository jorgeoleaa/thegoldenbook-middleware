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

public class SubjectDAOImpl implements SubjectDAO{
	
	private static Logger logger = LogManager.getLogger(SubjectDAOImpl.class);
	
	public SubjectDAOImpl() {
		
	}
	
	public List<Subject> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> results = new ArrayList<Subject>();
		
		try {
			
			StringBuilder query = new StringBuilder (" select s.id, sl.name as format_name, s.parent_id, parent_sl.name as parent_name ")
					.append(" from subject s ")
					.append(" inner join subject_language sl on sl.subject_id = s.id ")
					.append(" left join subject parent_s ON parent_s.id = s.parent_id ")
					.append(" left join subject_language parent_sl on parent_sl.subject_id = parent_s.id and parent_sl.language_id = sl.language_id ")
					.append(" inner join language lang ON lang.id = sl.language_id ")
					.append(" where lang.locale =  ?");
			 
			pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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

	
	public List<Subject> findByBook(Connection con, String locale,  Long bookId) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Subject> results = new ArrayList<Subject>();
		
		try {
			StringBuilder query = new StringBuilder(" select s.id, sl.name as format_name, s.parent_id, parent_sl.name as parent_name ")
					.append(" from subject s ")
					.append(" inner join subject_language sl on sl.subject_id = s.id ")
					.append(" left join subject parent_s ON parent_s.id = s.parent_id ")
					.append(" left join subject_language parent_sl on parent_sl.subject_id = parent_s.id and parent_sl.language_id = sl.language_id ")
					.append(" inner join language lang ON lang.id = sl.language_id ")
					.append(" inner join book_subject bs on bs.subject_id = s.id ")
					.append(" inner join book b on b.id = bs.book_id ")
					.append(" where lang.locale = ? and b.id = ? ");

			
			pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, bookId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				results.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error("BookID: "+bookId+" locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return results;
		
	}
	
	
	public Subject findById(Connection con, int id, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		Subject subject = null;
		
		try {
			
			StringBuilder query = new StringBuilder (" select s.id, sl.name as format_name, s.parent_id, parent_sl.name as parent_name ")
					.append(" from subject s ")
					.append(" inner join subject_language sl on sl.subject_id = s.id ")
					.append(" left join subject parent_s ON parent_s.id = s.parent_id ")
					.append(" left join subject_language parent_sl on parent_sl.subject_id = parent_s.id and parent_sl.language_id = sl.language_id ")
					.append(" inner join language lang ON lang.id = sl.language_id ")
					.append(" where lang.locale =  ? and s.id = ? ");
			 
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			pst.setInt(i++, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				subject = loadNext(rs);
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return subject;	
	}
	
	protected Subject loadNext(ResultSet rs) throws SQLException{
		
		Subject t = new Subject();
		int i = 1;
		
		t.setId(rs.getInt(i++));
		t.setName(rs.getString(i++));
		t.setParentId(rs.getInt(i++));
		t.setParentName(rs.getString(i++));
		
		return t;
	}


	
}
