package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.GeneroLiterario;

public interface GeneroLiterarioDAO {
	
	public List<GeneroLiterario> findAll(Connection con, String locale) throws DataException;
}
