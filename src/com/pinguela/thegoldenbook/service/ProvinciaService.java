package com.pinguela.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.Provincia;

public interface ProvinciaService {
	
	public Provincia findById(int id) throws DataException;
	public List<Provincia>findAll() throws DataException;
}
