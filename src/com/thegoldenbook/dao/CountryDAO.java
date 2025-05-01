package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Country;

public interface CountryDAO {
	
	public List<Country> findAll(Connection con, String locale) throws DataException;
	public Country findById (Connection con, int id, String locale) throws DataException;
}
