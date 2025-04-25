package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Provincia;

public interface ProvinciaDAO {

	public Provincia findById(Connection con, int id) throws DataException;
	public List<Provincia>findAll (Connection con) throws DataException;
}
