package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;

public interface ValoracionService {
	
	public Results<Review> findByValoracionCriteria(ReviewCriteria i, int pos, int pageSize)
			throws DataException;
	
	public Review findByValoracion (Long clienteId, Long libroId) 
			throws DataException;
	
	public Results<Review> findByCliente(Long clienteId, int pos, int pageSize)
			throws DataException;
	
	public Results<Review> findByLibro(Long libroId, int pos, int pageSize)
			throws DataException;
	
	public void create (Review v, String locale)
			throws DataException;
	
	public boolean delete (Long clienteId, Long libroId)
			throws DataException;
	
	public boolean update (Review v)
			throws DataException;
	
	public Double calcularMedia (List<Review> valoraciones) 
		throws DataException;
	
}
