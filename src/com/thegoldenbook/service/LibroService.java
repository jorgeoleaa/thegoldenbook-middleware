package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;

public interface LibroService {
	
	public Book findByLibro (String locale, Long id) 
			throws DataException;
	
	public Results<Book> findByCriteria (LibroCriteria l, int pos, int pageSize) 
			throws DataException;
	
	public Long create (String locale, Book l)
			throws DataException;
	
	public boolean update (Book l)
			throws DataException;
}
