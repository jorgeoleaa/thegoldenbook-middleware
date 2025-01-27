package com.pinguela.thegoldenbook.service;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.EmpleadoDTO;
import com.pinguela.thegoldenbook.model.Results;

public interface EmpleadoService {
	
	public EmpleadoDTO autenticar(Long id, String password) 
			throws DataException;
	
	public EmpleadoDTO findBy(Long id) 
			throws DataException, ServiceException;
	
	public Results<EmpleadoDTO> findAll(int pos, int pageSize)
			throws DataException;
	
	public boolean delete (Long id)
		throws DataException, ServiceException;
	
	public boolean update (EmpleadoDTO empl) 
		throws DataException;
	
	public boolean updatePassword (String password, Long id) 
			throws DataException;
	
	public Long registrar (EmpleadoDTO empl)
		throws DataException, ServiceException;
}
