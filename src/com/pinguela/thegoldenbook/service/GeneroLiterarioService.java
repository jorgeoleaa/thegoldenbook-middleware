package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.GeneroLiterario;

public interface GeneroLiterarioService {
	
	public List<GeneroLiterario> findAll(String locale) throws DataException;
}
