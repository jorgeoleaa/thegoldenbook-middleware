package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.Provincia;

public interface ProvinciaDAO {

	public Provincia findById(Connection con, int id) throws DataException;
	public List<Provincia>findAll (Connection con) throws DataException;
}
