package com.pinguela.thegoldenbook.service;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.Results;

public interface ClienteService {
	
	public Results<ClienteDTO> findAll(int pos, int pageSize)
			throws DataException;
	
	public ClienteDTO findById (Long id)
			throws DataException;
	
	public ClienteDTO findByNick (String nick)
			throws DataException;
	
	public ClienteDTO findByEmail (String mail)
			throws DataException;
	
	public Long registrar(ClienteDTO c) 
			throws DataException, ServiceException;
	
	public boolean update (ClienteDTO c) 
			throws DataException;
	
	public boolean updatePassword (String password, Long id) 
			throws DataException;
	
	public boolean delete (Long id) 
			throws DataException, ServiceException;
	
	public ClienteDTO autenticar (String mail, String password)
			throws DataException;


}
