package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Localidad;

public interface LocalidadService {

	public List<Localidad> findAll() 
			throws DataException;
	
	public Localidad findById(int id)
			throws DataException;
	
	public Localidad findByCodigoPostal (int codigoPostal)
			throws DataException;
}
