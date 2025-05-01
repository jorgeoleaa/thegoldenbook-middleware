package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.ReadingAgeGroup;

public interface ReadingAgeGroupDAO {
	
	public List<ReadingAgeGroup> findAll(Connection con, String locale)
		throws DataException;
}
