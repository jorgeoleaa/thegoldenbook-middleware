package com.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Pais;

public interface PaisService {
	
	public List<Pais> findAll() throws DataException;
	public Pais findById (int id) throws DataException;
}
