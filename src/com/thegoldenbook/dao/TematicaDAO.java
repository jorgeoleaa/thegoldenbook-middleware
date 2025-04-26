package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Subject;

public interface TematicaDAO {
	
	public List<Subject> findAll(Connection con, String locale)
			throws DataException;
	
	public List<Subject> findByLibro(Connection con, String locale, Long libroId)
			throws DataException;
	
	public Subject findById(Connection con, int id)
			throws DataException;
}
