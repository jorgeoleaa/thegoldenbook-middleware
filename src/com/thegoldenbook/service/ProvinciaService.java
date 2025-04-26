package com.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Region;

public interface ProvinciaService {
	
	public Region findById(int id) throws DataException;
	public List<Region>findAll() throws DataException;
}
