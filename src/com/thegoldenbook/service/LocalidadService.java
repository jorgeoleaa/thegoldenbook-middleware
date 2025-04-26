package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Locality;

public interface LocalidadService {

	public List<Locality> findAll() 
			throws DataException;
	
	public Locality findById(int id)
			throws DataException;
	
	public Locality findByCodigoPostal (int codigoPostal)
			throws DataException;
}
