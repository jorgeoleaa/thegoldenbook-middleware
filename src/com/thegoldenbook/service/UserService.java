package com.thegoldenbook.service;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Results;

public interface UserService {
	
	public Results<User> findAll(String locale, int pos, int pageSize)
			throws DataException;
	
	public User findById (Long id, String locale)
			throws DataException;
	
	public User findByNick (String nick, String locale)
			throws DataException;
	
	public User findByEmail (String email, String locale)
			throws DataException;
	
	public Long register(User user) 
			throws DataException, ServiceException;
	
	public boolean update (User users) 
			throws DataException;
	
	public boolean updatePassword (String password, Long id) 
			throws DataException;
	
	public boolean delete (Long id, String locale) 
			throws DataException, ServiceException;
	
	public User authenticate (String email, String password, String locale)
			throws DataException;


}
