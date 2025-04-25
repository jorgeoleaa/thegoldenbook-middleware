package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.DireccionDTO;

public interface DireccionService {

	public boolean delete (Long id) throws DataException;
	public boolean update (DireccionDTO d) throws DataException;
	public Long create (DireccionDTO d) throws DataException;
	
}
