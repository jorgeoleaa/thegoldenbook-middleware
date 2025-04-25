package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Formato;

public interface FormatoService {
	
	public List<Formato> findAll(String locale)
			throws DataException;

}
