package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.AddressDAO;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.util.JDBCUtils;

public class AddressDAOImpl implements AddressDAO {

	private static Logger logger = LogManager.getLogger(AddressDAOImpl.class);

	public AddressDAOImpl() {

	}

	public Address findByEmployeeId(Connection con, Long employeeId, String locale) throws DataException{

		Address address = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" select a.id as address_id, a.street_name, a.address_line_2, a.user_id, a.employee_id, a.locality_id, ll.name as locality_name, r.id as region_id, rl.name as region_name, c.id as country_id, cl.name as country_name ")
					.append(" from address a ")
					.append(" inner join locality l on l.id = a.locality_id ")
					.append(" inner join locality_language ll on ll.locality_id = l.id ")
					.append(" inner join region r on r.id = l.region_id ")
					.append(" inner join region_language rl on rl.region_id = r.id ")
					.append(" inner join country c on c.id = r.country_id ")
					.append(" inner join country_language cl on cl.country_id = c.id ")
					.append(" inner join language lang on lang.id = ll.language_id and lang.id = rl.language_id and lang.id = cl.language_id")
					.append(" where a.employee_id = ? and lang.locale = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, employeeId);
			pst.setString(i++, locale);

			rs = pst.executeQuery();

			if(rs.next()) {
				address = loadNext(rs);
			}

		}catch (SQLException e) {
			logger.error("Employee id: "+employeeId+" locale"+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return address;
	}


	public Address findByUserId(Connection con, Long userId, String locale) throws DataException{

		Address address = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
		

		StringBuilder query = new StringBuilder(" select a.id as address_id, a.street_name, a.address_line_2, a.user_id, a.employee_id, a.locality_id, ll.name as locality_name, r.id as region_id, rl.name as region_name, c.id as country_id, cl.name as country_name ")
				.append(" from address a ")
				.append(" inner join locality l on l.id = a.locality_id ")
				.append(" inner join locality_language ll on ll.locality_id = l.id ")
				.append(" inner join region r on r.id = l.region_id ")
				.append(" inner join region_language rl on rl.region_id = r.id ")
				.append(" inner join country c on c.id = r.country_id ")
				.append(" inner join country_language cl on cl.country_id = c.id ")
				.append(" inner join language lang on lang.id = ll.language_id and lang.id = rl.language_id and lang.id = cl.language_id")
				.append(" where a.user_id = ? and lang.locale = ? ");
		
		
		pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		int i = 1;
		pst.setLong(i++, userId);
		pst.setString(i++, locale);

		rs = pst.executeQuery();
		
		if(rs.next()) {
			address = loadNext(rs);
		}
		
		}catch (SQLException e) {
			logger.error("User id: "+userId+", Locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return address;
	}
	
	


	public boolean delete(Connection con, Long id) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(" delete from address where id = ? ");

			int i = 1;

			pst.setLong(i++, id);

			int deletedRows = pst.executeUpdate();

			if(deletedRows == 0) {
				return false;
			}


		} catch (SQLException e) {
			logger.error("Address ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public boolean update(Connection con, Address address) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(" update address set street_name = ?, address_line_2 = ?, locality_id = ?, user_id = ?, employee_id = ? "
					+" where id = ?");

			int i = 1;
			pst.setString(i++, address.getStreetName());
			pst.setString(i++, address.getAddressLine2());
			pst.setInt(i++, address.getLocalityId());
			JDBCUtils.setNullable(pst, i++, address.getUserId());
			JDBCUtils.setNullable(pst, i++, address.getEmployeeId());
			pst.setLong(i++, address.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;
			}

		} catch (SQLException e) {
			logger.error("Address: "+address, e);
			throw new DataException (e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public Long create(Connection con, Address address) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = con.prepareStatement(" insert into address (street_name, address_line_2, locality_id, user_id, employee_id)"
					+ " values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			JDBCUtils.setNullable(pst, i++, address.getStreetName());
			JDBCUtils.setNullable(pst, i++, address.getAddressLine2());
			JDBCUtils.setNullable(pst, i++, address.getLocalityId());				
			JDBCUtils.setNullable(pst, i++, address.getUserId());
			JDBCUtils.setNullable(pst, i++, address.getEmployeeId());

			int insertedRows = pst.executeUpdate();

			if(insertedRows != 1) {

			}

			rs = pst.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				address.setId(id);
				return id;
			}

		} catch(SQLException e) {
			logger.error("Address: "+address, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}

		return null;
	}

	public boolean deleteByEmployee(Connection con, Long employeeId) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = con.prepareStatement(" delete from address where employee_id = ? ");

			int i = 1;
			pst.setLong(i++, employeeId);

			int deletedRows = pst.executeUpdate();

			if(deletedRows == 0) {
				return false;
			}

		} catch(SQLException e) {
			logger.error("Employee ID: "+employeeId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return true;
	}

	protected Address loadNext (ResultSet rs) throws SQLException{

		int i = 1;

		Address d = new Address();

		d.setId(rs.getLong(i++));
		d.setStreetName(rs.getString(i++));
		d.setAddressLine2(rs.getString(i++));
		d.setUserId(JDBCUtils.getNullableLong(rs, i++));
		d.setEmployeeId(JDBCUtils.getNullableLong(rs, i++));
		d.setLocalityId(rs.getInt(i++));
		d.setLocalityName(rs.getString(i++));
		d.setRegionId(rs.getInt(i++));
		d.setRegionName(rs.getString(i++));
		d.setCountryId(rs.getInt(i++));
		d.setCountryName(rs.getString(i++));

		return d;
	}
}
