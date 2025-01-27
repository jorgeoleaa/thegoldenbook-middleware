package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.Pais;

public interface PaisDAO {
	
	public List<Pais> findAll(Connection con) throws DataException;
	public Pais findById (Connection con, int id) throws DataException;
}
