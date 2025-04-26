package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Locality;

public interface LocalidadDAO {
	
	public Locality findById(Connection con, int id) throws DataException;
	public List<Locality> findAll (Connection con) throws DataException;
	public Locality findByCodigoPostal (Connection con, int codigoPostal) throws DataException;
}
