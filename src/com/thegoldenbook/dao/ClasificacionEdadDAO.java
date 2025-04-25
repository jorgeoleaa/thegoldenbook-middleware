package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.ClasificacionEdad;

public interface ClasificacionEdadDAO {
	
	public List<ClasificacionEdad> findAll(Connection con, String locale)
		throws DataException;
}
