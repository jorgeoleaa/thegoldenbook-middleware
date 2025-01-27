package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.Idioma;

public interface IdiomaDAO {
	
	public List<Idioma> findAll(Connection con, String locale)
			throws DataException;
	
	public Idioma findById(Connection con, String locale, int id)
			throws DataException;
}
