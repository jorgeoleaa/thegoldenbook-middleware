package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;

public interface AuthorDAO {
	
	public Author findByAuthor(Connection con, Long id)
			throws DataException;
	
	public List<Author> findByBook(Connection con, Long bookId)
			throws DataException;
	
	public Results<Author> findAll (Connection con, int pos, int pageSize)
			throws Exception;
	
	public Long create (Connection con, Author a)
			throws DataException;
	
	public boolean update(Connection con, Author a)
			throws DataException;
	

}
