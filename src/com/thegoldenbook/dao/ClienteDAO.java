package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Results;

public interface ClienteDAO {
	
	public Results<User> findAll(Connection con, int pos, int pageSize)
		throws DataException;
	
	public User findById(Connection con, Long id)
		throws DataException;
	
	public User findByNick (Connection con, String nick)
			throws DataException;
	
	public User findByEmail (Connection con, String mail)
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
