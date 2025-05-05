package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.thegoldenbook.dao.UserDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.impl.UserDAOImpl;
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.UserService;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.ServiceException;
import com.thegoldenbook.util.JDBCUtils;

public class ClienteServiceImpl implements UserService {

	/**
	 * Es un objeto stateless, no tiene sentido instanciarlo multiples veces.
	 * (NOTA: Para segundo curso de DAW: Será un atributo, no una constante,
	 * para que podamos inyectarlo, y si queremos, cambiar el Encryptor). 
	 */
	public static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR 
	= new StrongPasswordEncryptor();

	private MailService mailService = null;
	private UserDAO clienteDAO = null;
	private static Logger logger = LogManager.getLogger(ClienteServiceImpl.class);


	public ClienteServiceImpl() {
		clienteDAO= new UserDAOImpl();
		mailService = new MailServiceImpl();
	}

	public User findById(Long id) throws DataException {

		Connection con = null;
		User cliente = new User();
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			cliente = clienteDAO.findById(con, id);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;

	}

	public User findByNick(String nick) throws DataException {

		Connection con = null;
		User cliente = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			cliente = clienteDAO.findByNick(con, nick);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;
	}




	public User findByEmail(String mail) throws DataException {

		Connection con = null;
		User cliente = new User();
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(commit);
			cliente = clienteDAO.findByEmail(con, mail);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException();
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;
	}

	public Results<User> findAll(int pos, int pageSize) throws DataException {

		Connection con = null;
		boolean commit = false;
		Results<User> resultados = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = clienteDAO.findAll(con, pos, pageSize);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}

	public Long registrar(User c) 
			throws DataException, ServiceException{
		
		c.setPassword(PASSWORD_ENCRYPTOR.
				encryptPassword(c.getPassword()));

		Long id = null;
		Connection con = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = clienteDAO.create(con, c);
			mailService.sendBienvenida(c.getEmail(), c);

			commit = true;


		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}


	public boolean delete(Long id) 
			throws DataException, ServiceException{

		Connection con = null;
		boolean c = false;
		User cliente = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			cliente = clienteDAO.findById(con, id);
			c = clienteDAO.delete(con, id);
			mailService.enviar(cliente.getEmail(), "Eliminación de cuenta!", "Gracias por haber formado parte de esta comunidad. Te echaremos de menos");
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
		boolean cliente = false;
		boolean commit = false;
		try {


			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			
			cliente = clienteDAO.updatePassword(con, PASSWORD_ENCRYPTOR.encryptPassword(password), id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;
	}


	public boolean update(User c) 
			throws DataException{

		Connection con = null;
		boolean cliente = false;
		boolean commit = false;
		try {

			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);

			cliente = clienteDAO.update(con, c);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;
	}

	@Override
	public User autenticar(String mail, String password) throws DataException {
		
		Connection con = null;
		boolean commit = false;
		User cliente = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			cliente = clienteDAO.findByEmail(con, mail);
			if(cliente == null) {
				return null;
			}
			if(!PASSWORD_ENCRYPTOR.checkPassword(password, cliente.getPassword())) {
				cliente = null;
			}
			
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		
		return cliente;
	}


}
