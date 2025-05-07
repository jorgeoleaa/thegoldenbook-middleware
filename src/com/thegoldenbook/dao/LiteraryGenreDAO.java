package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.LiteraryGenre;
import com.thegoldenbook.model.Subject;

public interface LiteraryGenreDAO {
	
	public List<LiteraryGenre> findAll(Connection con, String locale) throws DataException;
	
	public List<LiteraryGenre> findByBook(Connection con, String locale, Long libroId)
			throws DataException;
	
	public LiteraryGenre findById(Connection con, int id, String locale)
			throws DataException;
}
