package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Subject;

public interface SubjectDAO {
	
	public List<Subject> findAll(Connection con, String locale)
			throws DataException;
	
	public List<Subject> findByBook(Connection con, String locale, Long libroId)
			throws DataException;
	
	public Subject findById(Connection con, int id, String locale)
			throws DataException;
}
