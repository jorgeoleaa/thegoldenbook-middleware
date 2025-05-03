package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Region;

public interface RegionDAO {

	public Region findById(Connection con, int id, String locale) throws DataException;
	public List<Region>findAll (Connection con, String locale) throws DataException;
}
