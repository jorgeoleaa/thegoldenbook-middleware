package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Format;

public interface FormatDAO {
	
	public List<Format> findAll(Connection con, String locale)
			throws DataException;
}
