package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.LiteraryGenre;

public interface GeneroLiterarioService {
	
	public List<LiteraryGenre> findAll(String locale) throws DataException;
}
