package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Tematica;

public interface TematicaDAO {
	
	public List<Tematica> findAll(Connection con, String locale)
			throws DataException;
	
	public List<Tematica> findByLibro(Connection con, String locale, Long libroId)
			throws DataException;
	
	public Tematica findById(Connection con, int id)
			throws DataException;
}
