package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Locality;

public interface LocalityDAO {
	
	public Locality findById(Connection con, int id, String locale) throws DataException;
	public List<Locality> findAll (Connection con, String locale) throws DataException;
	public Locality findByPostalCode (Connection con, String postalCode, String locale) throws DataException;
}
