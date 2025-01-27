package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.DireccionDTO;

public interface DireccionService {

	public boolean delete (Long id) throws DataException;
	public boolean update (DireccionDTO d) throws DataException;
	public Long create (DireccionDTO d) throws DataException;
	
}
