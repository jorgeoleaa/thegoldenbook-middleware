package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.Idioma;

public interface IdiomaService {
	
	public List<Idioma> findAll(String locale)
			throws DataException;
	
	public Idioma findById(String locale, int id)
			throws DataException;
}
