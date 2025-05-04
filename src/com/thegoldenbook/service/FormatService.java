package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Format;

public interface FormatService {
	
	public List<Format> findAll(String locale)
			throws DataException;

}
