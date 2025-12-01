package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Country;

public interface CountryDAO {
	
	public List<Country> findAll(String locale);
	public Country findById (int id, String locale);
}
