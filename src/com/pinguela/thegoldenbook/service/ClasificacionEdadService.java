package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.ClasificacionEdad;

public interface ClasificacionEdadService {
	
	public List<ClasificacionEdad> findAll(String locale)
			throws DataException;
	
}
