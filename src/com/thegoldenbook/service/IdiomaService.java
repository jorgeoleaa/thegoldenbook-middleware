package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Idioma;

public interface IdiomaService {
	
	public List<Idioma> findAll(String locale)
			throws DataException;
	
	public Idioma findById(String locale, int id)
			throws DataException;
}
