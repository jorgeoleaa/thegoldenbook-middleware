package com.pinguela.thegoldenbook.service;

import java.util.List;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.model.ValoracionDTO;

public interface ValoracionService {
	
	public Results<ValoracionDTO> findByValoracionCriteria(ValoracionCriteria i, int pos, int pageSize)
			throws DataException;
	
	public ValoracionDTO findByValoracion (Long clienteId, Long libroId) 
			throws DataException;
	
	public Results<ValoracionDTO> findByCliente(Long clienteId, int pos, int pageSize)
			throws DataException;
	
	public Results<ValoracionDTO> findByLibro(Long libroId, int pos, int pageSize)
			throws DataException;
	
	public void create (ValoracionDTO v, String locale)
			throws DataException;
	
	public boolean delete (Long clienteId, Long libroId)
			throws DataException;
	
	public boolean update (ValoracionDTO v)
			throws DataException;
	
	public Double calcularMedia (List<ValoracionDTO> valoraciones) 
		throws DataException;
	
}
