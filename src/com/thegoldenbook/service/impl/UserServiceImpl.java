package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.thegoldenbook.dao.AddressDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.UserDAO;
import com.thegoldenbook.dao.impl.AddressDAOImpl;
import com.thegoldenbook.dao.impl.UserDAOImpl;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.User;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.ServiceException;
import com.thegoldenbook.service.UserService;
import com.thegoldenbook.util.JDBCUtils;

public class UserServiceImpl implements UserService {

	/**
	 * This is a stateless object; it doesn't make sense to instantiate it multiple times.
	 * (NOTE: For the second year of DAW: It will be an attribute, not a constant,
	 * so that we can inject it and, if desired, replace the Encryptor.)
	 */

	public static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR 
	= new StrongPasswordEncryptor();

	private MailService mailService = null;
	private UserDAO userDAO = null;
	private AddressDAO addressDAO = null;
	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);


	public UserServiceImpl() {
		userDAO= new UserDAOImpl();
		mailService = new MailServiceImpl();
		addressDAO = new AddressDAOImpl();
	}

	public User findById(Long id, String locale) throws DataException {

		Connection con = null;
		User user = new User();
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			user = userDAO.findById(con, id, locale);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return user;

	}

	public User findByNick(String nick, String locale) throws DataException {

		Connection con = null;
		User user = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			user = userDAO.findByNick(con, nick, locale);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return user;
	}




	public User findByEmail(String email, String locale) throws DataException {

		Connection con = null;
		User user = new User();
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(commit);
			user = userDAO.findByEmail(con, email, locale);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return user;
	}

	public Results<User> findAll(String locale, int pos, int pageSize) throws DataException {

		Connection con = null;
		boolean commit = false;
		Results<User> results = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = userDAO.findAll(con, locale, pos, pageSize);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}

	public Long register(User user) 
			throws DataException, ServiceException{
		
		user.setPassword(PASSWORD_ENCRYPTOR.
				encryptPassword(user.getPassword()));

		Long id = null;
		Connection con = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = userDAO.create(con, user);
			mailService.sendWelcome(user.getEmail(), user);

			commit = true;


		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}


	public boolean delete(Long id, String locale) 
			throws DataException, ServiceException{

		Connection con = null;
		boolean c = false;
		User user = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			user = userDAO.findById(con, id, locale);
			addressDAO.delete(con, user.getAddresses().get(0).getId());
			c = userDAO.delete(con, id);
			mailService.send(user.getEmail(), "Account Deletion", "Thank you for being part of this community. We will miss you.");
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return c;

	}

	public boolean updatePassword(String password, Long id) throws DataException{
		Connection con = null;
		boolean user = false;
		boolean commit = false;
		try {


			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			
			user = userDAO.updatePassword(con, PASSWORD_ENCRYPTOR.encryptPassword(password), id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return user;
	}


	public boolean update(User c) 
			throws DataException{

		Connection con = null;
		boolean user = false;
		boolean commit = false;
		try {

			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);

			user = userDAO.update(con, c);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return user;
	}

	@Override
	public User authenticate(String email, String password, String locale) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		User user = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			user = userDAO.findByEmail(con, email, locale);
			if(user == null) {
				return null;
			}
			if(!PASSWORD_ENCRYPTOR.checkPassword(password, user.getPassword())) {
				user = null;
			}
			
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		
		return user;
	}


}
