package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.BookCriteria;

public interface BookDAO {
	
	public Long create(Connection con, String locale, Book book)
			throws DataException;
	
	public boolean update(Connection con, Book book)
			throws DataException;
	
	public Results<Book> findByCriteria(Connection con, BookCriteria bookCrtieria, int pos, int pageSize)
			throws DataException;
	
	public Book findByBook (Connection con, String locale, Long id)
			throws DataException;
	
	public void assignAuthors (Connection con, Long bookId, List<Long> authorsId)
			throws DataException;
	
	public void assignSubjects (Connection con, String locale, Long bookId, List<Integer>subjectsId)
			throws DataException;
	
	public void assignLiteraryGenres (Connection con, String locale, Long bookId, List<Integer>literaryGenresId)
			throws DataException;
}
