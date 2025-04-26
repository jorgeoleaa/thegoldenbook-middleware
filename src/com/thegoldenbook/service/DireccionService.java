package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Address;

public interface DireccionService {

	public boolean delete (Long id) throws DataException;
	public boolean update (Address d) throws DataException;
	public Long create (Address d) throws DataException;
	
}
