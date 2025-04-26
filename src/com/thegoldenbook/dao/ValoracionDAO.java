package com.thegoldenbook.dao;

import java.sql.Connection;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ValoracionCriteria;

public interface ValoracionDAO {
	
	public Results<Review> findByValoracionCriteria (Connection con, ValoracionCriteria i, int pos, int pageSize)
			throws DataException;
	
	public Review findByValoracion (Connection con, Long clienteId, Long libroId) 
			throws DataException;
	
	public Results<Review> findByCliente (Connection con, Long clienteId, int pos, int pageSize)
			throws DataException;
	
	public Results<Review> findByLibro (Connection con, Long libroId, int pos, int pageSize)
			throws DataException;
	
	public void create (Connection con, Review v)
			throws DataException;
	
	public boolean delete (Connection con, Long clienteId, Long libroId)
			throws DataException;
	
	public boolean update (Connection con, Review v)
			throws DataException;
	

}
