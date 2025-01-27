package com.pinguela.thegoldenbook.service;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;

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
