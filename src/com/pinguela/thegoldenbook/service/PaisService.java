package com.pinguela.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.Pais;

public interface PaisService {
	
	public List<Pais> findAll() throws DataException;
	public Pais findById (int id) throws DataException;
}
