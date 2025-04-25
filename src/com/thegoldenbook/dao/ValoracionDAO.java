package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.ValoracionDTO;
import com.thegoldenbook.service.ValoracionCriteria;

public interface ValoracionDAO {
	
	public Results<ValoracionDTO> findByValoracionCriteria (Connection con, ValoracionCriteria i, int pos, int pageSize)
			throws DataException;
	
	public ValoracionDTO findByValoracion (Connection con, Long clienteId, Long libroId) 
			throws DataException;
	
	public Results<ValoracionDTO> findByCliente (Connection con, Long clienteId, int pos, int pageSize)
			throws DataException;
	
	public Results<ValoracionDTO> findByLibro (Connection con, Long libroId, int pos, int pageSize)
			throws DataException;
	
	public void create (Connection con, ValoracionDTO v)
			throws DataException;
	
	public boolean delete (Connection con, Long clienteId, Long libroId)
			throws DataException;
	
	public boolean update (Connection con, ValoracionDTO v)
			throws DataException;
	

}
