package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;

public interface BookService {
	
	public Book findByBook (String locale, Long id) 
			throws DataException;
	
	public Results<Book> findByCriteria (BookCriteria bookCriteria, int pos, int pageSize) 
			throws DataException;
	
	public Long create (String locale, Book book)
			throws DataException;
	
	public boolean update (Book book)
			throws DataException;
}
