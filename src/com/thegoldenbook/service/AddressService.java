package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Address;

public interface AddressService {

	public boolean delete (Long id) throws DataException;
	public boolean update (Address a) throws DataException;
	public Long create (Address a) throws DataException;
	
}
