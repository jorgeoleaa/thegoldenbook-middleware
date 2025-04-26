package com.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Country;

public interface PaisService {
	
	public List<Country> findAll() throws DataException;
	public Country findById (int id) throws DataException;
}
