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
import com.thegoldenbook.dao.LiteraryGenreDAO;
import com.thegoldenbook.model.LiteraryGenre;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.util.JDBCUtils;

public class LiteraryGenreDAOImpl implements LiteraryGenreDAO{
	
	private Logger logger = LogManager.getLogger(LiteraryGenreDAOImpl.class);
	
	public List<LiteraryGenre> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<LiteraryGenre> genres = new ArrayList<LiteraryGenre>();
		
		try {
			StringBuilder query = new StringBuilder (" select lg.id, lgl.name AS genre_name, lg.parent_id, parent_lgl.name AS parent_name ")
					.append(" from literary_genre lg ")
					.append(" inner join literary_genre_language lgl on lgl.literary_genre_id = lg.id ")
					.append(" left join literary_genre parent_lg on parent_lg.id = lg.parent_id ")
					.append(" left join literary_genre_language parent_lgl on parent_lgl.literary_genre_id = parent_lg.id and parent_lgl.language_id = lgl.language_id ")
					.append(" inner join language lang on lang.id = lgl.language_id ")
					.append(" where lang.locale = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				genres.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return genres;
	}

	
	protected LiteraryGenre loadNext (ResultSet rs) throws SQLException{
		
		LiteraryGenre genre = new LiteraryGenre();
		
		int i = 1;
		genre.setId(rs.getInt(i++));
		genre.setName(rs.getString(i++));
		genre.setParentId(rs.getInt(i++));
		genre.setParentName(rs.getString(i++));
		
		return genre;
	}


	@Override
	public List<LiteraryGenre> findByBook(Connection con, String locale, Long bookId) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<LiteraryGenre> results = new ArrayList<LiteraryGenre>();
		
		try {
			StringBuilder query = new StringBuilder(" select lg.id, lgl.name, lg.parent_id, parent_lgl.name as parent_name ")
					.append(" from literary_genre lg ")
					.append(" inner join literary_genre_language lgl on lgl.literary_genre_id = lg.id  ")
					.append(" left join literary_genre parent_lg ON parent_lg.id = lg.parent_id ")
					.append(" left join literary_genre_language parent_lgl on parent_lgl.literary_genre_id = parent_lg.id and parent_lgl.language_id = lgl.language_id  ")
					.append(" inner join language lang ON lang.id = lgl.language_id  ")
					.append(" inner join book_literary_genre blg on blg.literary_genre_id = lg.id  ")
					.append(" inner join book b on b.id = blg.book_id ")
					.append(" where lang.locale = ? and b.id = ?");

			
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


	@Override
	public LiteraryGenre findById(Connection con, int id, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		LiteraryGenre literaryGenre = null;
		
		try {
		
		StringBuilder query = new StringBuilder(" select lg.id, lgl.name, lg.parent_id, parent_lgl.name as parent_name")
				.append(" from literary_genre lg")
				.append(" inner join literary_genre_language lgl on lgl.literary_genre_id = lg.id ")
				.append(" left join literary_genre parent_lg ON parent_lg.id = lg.parent_id  ")
				.append(" left join literary_genre_language parent_lgl on parent_lgl.literary_genre_id = parent_lg.id and parent_lgl.language_id = lgl.language_id ")
				.append(" inner join language lang ON lang.id = lgl.language_id ")
				.append(" where lang.locale = ? and lg.id = ? ");
		
		pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		int i = 1;
		pst.setString(i++, locale);
		pst.setLong(i++, id);
		
		rs = pst.executeQuery();
		
		if(rs.next()) {
			literaryGenre = loadNext(rs);
		}
		
		}catch(SQLException e) {
			logger.error("LiteraryGenreId: "+id+" locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return literaryGenre;
		}

}
