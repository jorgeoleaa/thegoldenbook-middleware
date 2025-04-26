package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Results;

public interface ClienteService {
	
	public Results<User> findAll(int pos, int pageSize)
			throws DataException;
	
	public User findById (Long id)
			throws DataException;
	
	public User findByNick (String nick)
			throws DataException;
	
	public User findByEmail (String mail)
			throws DataException;
	
	public Long registrar(User c) 
			throws DataException, ServiceException;
	
	public boolean update (User c) 
			throws DataException;
	
	public boolean updatePassword (String password, Long id) 
			throws DataException;
	
	public boolean delete (Long id) 
			throws DataException, ServiceException;
	
	public User autenticar (String mail, String password)
			throws DataException;


}
