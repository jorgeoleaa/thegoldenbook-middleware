package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.AddressDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.UserDAO;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.User;
import com.thegoldenbook.util.JDBCUtils;

public class UserDAOImpl implements UserDAO {

	private static Logger logger = LogManager.getLogger(UserDAOImpl.class);
	
	private AddressDAO addressDAO = null;

	public UserDAOImpl() {
		addressDAO = new AddressDAOImpl();
	}


	public User findById(Connection con, Long id, String locale) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;

		try {
			StringBuilder query = new StringBuilder(" select u.id, u.nickname, u.name, u.last_name, u.second_last_name, u.national_id, u.email, u.phone_number, u.password, u.oauth_token, u.created_at, u.desactivated_at ")
					.append(" from user u ")
					.append(" where u.id = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				user = loadNext(rs, con, locale);
			}


		} catch(SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return user;
	}
	

	public User findByEmail(Connection con, String email, String locale) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		User user = null;

		try {
			StringBuilder query = new StringBuilder(" select u.id, u.nickname, u.name, u.last_name, u.second_last_name, u.national_id, u.email, u.phone_number, u.password, u.oauth_token, u.created_at, u.desactivated_at ")
					.append(" from user u ")
					.append(" where u.email = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, email);

			rs = pst.executeQuery();

			if(rs.next()) {
				user = loadNext(rs, con, locale);
			}
		}catch(SQLException e) {
			logger.error("Email: "+email, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return user;
	}



	public Results<User> findAll(Connection con, String locale, int pos, int pageSize) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Results<User> results = new Results<User>();

		try {
			StringBuilder query = new StringBuilder(" select u.id, u.nickname, u.name, u.last_name, u.second_last_name, u.national_id, u.email, u.phone_number, u.password, u.oauth_token, u.created_at, u.desactivated_at ")
					.append(" from user u ")
					.append(" order by name asc ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					results.getPage().add(loadNext(rs, con, locale));
					count++;
				}while (count<pageSize && rs.next());
			}
			results.setTotal(JDBCUtils.getTotalRows(rs));

		}catch(SQLException e) {
			logger.error(e);
			throw new DataException (e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return results;
	}

	public boolean delete(Connection conn, Long id) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = conn.prepareStatement("delete from user where id = ?");

			int i = 1;
			pst.setLong(i++, id);

			int deletedRows = pst.executeUpdate();

			if(deletedRows == 0) {
				return false;
			}


		} catch (SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}

		return true;
	}


	public boolean update(Connection conn, User user) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = conn.prepareStatement("update user set nickname = ?,  name = ?, last_name = ?, second_last_name = ?, national_id = ?, email = ?, phone_number = ?, oauth_token = ?, desactivated_at = ? "
					+" where id = ? ");

			int i = 1;
			pst.setString(i++, user.getNickname());
			pst.setString(i++, user.getName());
			pst.setString(i++, user.getLastName());
			pst.setString(i++, user.getSecondLastName());
			pst.setString(i++, user.getNationalId());
			pst.setString(i++, user.getEmail());
			pst.setString(i++, user.getPhoneNumber());
			pst.setString(i++, user.getOauthToken());
			pst.setDate(i++, user.getDesactivatedAt());
			pst.setLong(i++, user.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;
			}


		} catch (SQLException e) {
			logger.error("User: "+user, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}


		return true;
	}


	public boolean updatePassword(Connection con, String password, Long id) throws DataException {

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement("update user set password = ? "
					+ " where id = ? ");

			int i = 1;
			pst.setString(i++, password);
			pst.setLong(i++, id);

			int updatedRows = pst.executeUpdate();
			if(updatedRows == 0) {
				return false;
			}

		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}

	public Long create(Connection conn, User c) throws DataException{

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = conn.prepareStatement("insert into user(nickname, name, last_name, second_last_name, national_id, email, phone_number, password, oauth_token, created_at)"
					+" VALUES(?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			pst.setString(i++, c.getNickname());
			pst.setString(i++, c.getName());
			pst.setString(i++, c.getLastName());
			JDBCUtils.setNullable(pst, i++, c.getSecondLastName());			
			pst.setString(i++, c.getNationalId());
			pst.setString(i++, c.getEmail());
			pst.setString(i++, c.getPhoneNumber());
			pst.setString(i++, c.getPassword());
			pst.setString(i++, c.getOauthToken());
			pst.setDate(i++, new java.sql.Date(System.currentTimeMillis()));

			int insertedRows = pst.executeUpdate();

			if(insertedRows != 1) {

			} else {
				rs = pst.getGeneratedKeys();
				if(rs.next()) {
					Long id = rs.getLong(1);
					c.setId(id);
					return id;

				} 
			}
		} catch(SQLException e) {
			logger.error("ClienteDTO: "+c, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}
		return null;
	}


	public User findByNick(Connection con, String nick, String locale) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		User c = null;
		try {

			StringBuilder query = new StringBuilder(" select u.id, u.nickname, u.name, u.last_name, u.second_last_name, u.national_id, u.email, u.phone_number, u.password, u.oauth_token, u.created_at, u.desactivated_at ")
					.append(" from user u ")
					.append(" where u.nickname = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);

			int i = 1;
			pst.setString(i++, nick);

			rs = pst.executeQuery();

			if(rs.next()) {
				c = loadNext(rs, con, locale);
			}

		}catch(SQLException e) {
			logger.error("Nickname: "+nick, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return c;
	}

	protected User loadNext (ResultSet rs, Connection con, String locale) throws SQLException, DataException{

		int i = 1;
		
		User user = new User();

		user.setId(rs.getLong(i++));
		user.setNickname(rs.getString(i++));
		user.setName(rs.getString(i++));
		user.setLastName(rs.getString(i++));
		user.setSecondLastName(rs.getString(i++));
		user.setNationalId(rs.getString(i++));
		user.setEmail(rs.getString(i++));
		user.setPhoneNumber(rs.getString(i++));
		user.setPassword(rs.getString(i++));
		user.setOauthToken(rs.getString(i++));
		user.setCreatedAt(rs.getDate(i++));
		user.setDesactivatedAt(rs.getDate(i++));
		List<Address> newAddress = new ArrayList<Address>();
		newAddress.add(addressDAO.findByUserId(con, user.getId(), locale));
		user.setAddresses(newAddress);
		

		return user;
	}


}

