package com.thegoldenbook.service;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;

public interface AutorService {
	
	public Results<Author> findAll (int pos, int pageSize)
			throws Exception;
	
	public Author findByAutor(Long id)
			throws DataException;
	
	public List<Author> findByLibro(Long id)
			throws DataException;
	
	public Long create (Author a)
			throws DataException;
	
	public boolean update(Author a)
			throws DataException;
}
