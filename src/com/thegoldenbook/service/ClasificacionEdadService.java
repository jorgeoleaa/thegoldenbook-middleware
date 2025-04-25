package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.ClasificacionEdad;

public interface ClasificacionEdadService {
	
	public List<ClasificacionEdad> findAll(String locale)
			throws DataException;
	
}
