package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Country;

public interface CountryService {
	
	public List<Country> findAll(String locale) throws DataException;
	public Country findById (int id, String locale) throws DataException;
}
