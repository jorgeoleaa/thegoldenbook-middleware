package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.Formato;

public interface FormatoDAO {
	
	public List<Formato> findAll(Connection con, String locale)
			throws DataException;
}
