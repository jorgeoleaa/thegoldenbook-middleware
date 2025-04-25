package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.LibroDTO;
import com.thegoldenbook.model.Results;

public interface LibroService {
	
	public LibroDTO findByLibro (String locale, Long id) 
			throws DataException;
	
	public Results<LibroDTO> findByCriteria (LibroCriteria l, int pos, int pageSize) 
			throws DataException;
	
	public Long create (String locale, LibroDTO l)
			throws DataException;
	
	public boolean update (LibroDTO l)
			throws DataException;
}
