package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Autor;
import com.thegoldenbook.model.Results;

public interface AutorDAO {
	
	public Autor findByAutor(Connection con, Long id)
			throws DataException;
	
	public List<Autor> findByLibro(Connection con, Long libroId)
			throws DataException;
	
	public Results<Autor> findAll (Connection con, int pos, int pageSize)
			throws Exception;
	
	public Long create (Connection con, Autor a)
			throws DataException;
	
	public boolean update(Connection con, Autor a)
			throws DataException;
	

}
