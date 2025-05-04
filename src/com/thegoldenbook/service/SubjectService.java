package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Subject;

public interface SubjectService {
	
	public List<Subject> findAll(String locale)
			throws DataException;
	
	public List<Subject> findByBook(String locale, Long libroId)
			throws DataException;

}
