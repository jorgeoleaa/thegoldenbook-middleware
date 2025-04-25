package com.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Provincia;

public interface ProvinciaService {
	
	public Provincia findById(int id) throws DataException;
	public List<Provincia>findAll() throws DataException;
}
