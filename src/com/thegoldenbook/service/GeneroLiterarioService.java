package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.GeneroLiterario;

public interface GeneroLiterarioService {
	
	public List<GeneroLiterario> findAll(String locale) throws DataException;
}
