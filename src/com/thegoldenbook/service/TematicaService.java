package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Tematica;

public interface TematicaService {
	
	public List<Tematica> findAll(String locale)
			throws DataException;
	
	public List<Tematica> findByLibro(String locale, Long libroId)
			throws DataException;

}
