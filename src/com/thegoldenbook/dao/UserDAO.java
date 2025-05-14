package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Results;

public interface UserDAO {
	
	public Results<User> findAll(Connection con, String locale, int pos, int pageSize)
		throws DataException;
	
	public User findById(Connection con, Long id, String locale)
		throws DataException;
	
	public User findByNick (Connection con, String nick, String locale)
			throws DataException;
	
	public User findByEmail (Connection con, String email, String locale)
			throws DataException;
	
	public boolean delete (Connection conn, Long id)
		throws DataException;
	
	public boolean update (Connection conn, User c)
		throws DataException;
	
	public boolean updatePassword (Connection con, String password, Long id)
		throws DataException;
	
	public Long create (Connection conn, User c)
		throws DataException;
}
