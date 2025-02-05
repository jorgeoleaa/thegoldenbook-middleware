package com.pinguela.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.LibroCriteria;
public interface LibroDAO {
	
	public Long create(Connection con, String locale, LibroDTO l)
			throws DataException;
	
	public boolean update(Connection con, LibroDTO l)
			throws DataException;
	
	public Results<LibroDTO> findByCriteria(Connection con, LibroCriteria libro, int pos, int pageSize)
			throws DataException;
	
	public LibroDTO findByLibro (Connection con, String locale, Long id)
			throws DataException;
	
	public void asignarAutor (Connection con, Long libroId, List<Long> autoresId)
			throws DataException;
	
	public void asignarTematica (Connection con, String locale, Long libroId, List<Integer>tematicasId)
			throws DataException;
}
