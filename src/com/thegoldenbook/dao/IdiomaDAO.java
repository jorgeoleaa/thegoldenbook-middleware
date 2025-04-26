package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Language;

public interface IdiomaDAO {
	
	public List<Language> findAll(Connection con, String locale)
			throws DataException;
	
	public Language findById(Connection con, String locale, int id)
			throws DataException;
}
