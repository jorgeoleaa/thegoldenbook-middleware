package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Locality;

public interface LocalityService {

	public List<Locality> findAll(String locale) 
			throws DataException;
	
	public Locality findById(int id, String locale)
			throws DataException;
	
	public Locality findByPostalCode(int codigoPostal, String locale)
			throws DataException;
}
