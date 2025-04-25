package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Localidad;

public interface LocalidadDAO {
	
	public Localidad findById(Connection con, int id) throws DataException;
	public List<Localidad> findAll (Connection con) throws DataException;
	public Localidad findByCodigoPostal (Connection con, int codigoPostal) throws DataException;
}
