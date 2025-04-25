package com.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Autor;
import com.thegoldenbook.model.Results;

public interface AutorService {
	
	public Results<Autor> findAll (int pos, int pageSize)
			throws Exception;
	
	public Autor findByAutor(Long id)
			throws DataException;
	
	public List<Autor> findByLibro(Long id)
			throws DataException;
	
	public Long create (Autor a)
			throws DataException;
	
	public boolean update(Autor a)
			throws DataException;
}
