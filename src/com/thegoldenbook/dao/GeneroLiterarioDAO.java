package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.LiteraryGenre;

public interface GeneroLiterarioDAO {
	
	public List<LiteraryGenre> findAll(Connection con, String locale) throws DataException;
}
