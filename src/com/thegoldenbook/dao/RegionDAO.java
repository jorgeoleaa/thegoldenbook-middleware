package com.thegoldenbook.dao;

import java.sql.Connection;
import java.util.List;

import com.thegoldenbook.model.Region;

public interface RegionDAO {

	public Region findById(Connection con, int id) throws DataException;
	public List<Region>findAll (Connection con) throws DataException;
}
