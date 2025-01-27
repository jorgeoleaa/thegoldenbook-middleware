package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.Formato;

public interface FormatoService {
	
	public List<Formato> findAll(String locale)
			throws DataException;

}
