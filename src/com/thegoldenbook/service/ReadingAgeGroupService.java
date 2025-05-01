package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.ReadingAgeGroup;

public interface ReadingAgeGroupService {
	
	public List<ReadingAgeGroup> findAll(String locale)
			throws DataException;
	
}
