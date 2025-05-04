package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.LiteraryGenre;

public interface LiteraryGenreDAO {
	
	public List<LiteraryGenre> findAll(Connection con, String locale) throws DataException;
}
