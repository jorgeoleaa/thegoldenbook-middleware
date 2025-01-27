package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.Results;

public interface ClienteDAO {
	
	public Results<ClienteDTO> findAll(Connection con, int pos, int pageSize)
		throws DataException;
	
	public ClienteDTO findById(Connection con, Long id)
		throws DataException;
	
	public ClienteDTO findByNick (Connection con, String nick)
			throws DataException;
	
	public ClienteDTO findByEmail (Connection con, String mail)
			throws DataException;
	
	public boolean delete (Connection conn, Long id)
		throws DataException;
	
	public boolean update (Connection conn, ClienteDTO c)
		throws DataException;
	
	public boolean updatePassword (Connection con, String password, Long id)
		throws DataException;
	
	public Long create (Connection conn, ClienteDTO c)
		throws DataException;
}
