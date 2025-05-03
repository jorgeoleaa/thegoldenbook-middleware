package com.thegoldenbook.service;

import java.util.List;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.Region;

public interface RegionService {
	
	public Region findById(int id, String locale) throws DataException;
	public List<Region>findAll(String locale) throws DataException;
}
