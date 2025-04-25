package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;

public interface AutorDAO {
	
	public Author findByAutor(Connection con, Long id)
			throws DataException;
	
	public List<Author> findByLibro(Connection con, Long libroId)
			throws DataException;
	
	public Results<Author> findAll (Connection con, int pos, int pageSize)
			throws Exception;
	
	public Long create (Connection con, Author a)
			throws DataException;
	
	public boolean update(Connection con, Author a)
			throws DataException;
	

}
