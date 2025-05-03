package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.EmployeeDAO;
import com.thegoldenbook.dao.impl.EmpleadoDAOImpl;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.EmpleadoService;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.ServiceException;
import com.thegoldenbook.util.JDBCUtils;


public class EmpleadoServiceImpl implements EmpleadoService{

	public static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR 
	= new StrongPasswordEncryptor();

	private static Logger logger = LogManager.getLogger(EmpleadoServiceImpl.class);

	private EmployeeDAO empleadoDAO = null;
	private MailService mailService = null;

	public EmpleadoServiceImpl() {
		empleadoDAO = new EmpleadoDAOImpl();
		mailService = new MailServiceImpl();
	}

	public Results<Employee> findAll(int pos, int pageSize) throws DataException {

		Connection con = null;
		Boolean commit = false;
		Results<Employee> empleados = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			empleados = empleadoDAO.findAll(con, pos, pageSize);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return empleados;
	}

	public Employee findBy(Long id) 
			throws DataException, ServiceException{

		Connection con = null;
		Employee em = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			em = empleadoDAO.findBy(con, id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}

	public Employee autenticar(Long id, String password) throws DataException{

		Employee empleado = null;
		Connection con = null;
		boolean commit = false;


		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			empleado = empleadoDAO.findBy(con, id);

			if(empleado == null) {
				return null;
			}

			if(!PASSWORD_ENCRYPTOR.checkPassword(password, empleado.getPassword())) {
				empleado = null;
			} 
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return empleado;
	}

	public boolean delete(Long id) 
			throws DataException, ServiceException{

		Connection con = null;
		boolean em = false;
		Employee empl = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			empl = empleadoDAO.findBy(con, id);
			em = empleadoDAO.delete(con, id);
			mailService.enviar(empl.getEmail(), "Eliminación de su cuenta.", "Le enviamos este mensaje para comunicarle que debido a que ya no forma parte de nuestro equipo de trabajadores,"
					+ " su cuenta de empleado ha sido eliminada. "
					+ "Agradecemos sus servicios durante este tiempo.");

			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}


	public boolean update(Employee empl)
			throws DataException{

		Connection con = null;
		boolean em = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			em = empleadoDAO.update(con, empl);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}

	public boolean updatePassword(String password, Long id) throws DataException{
		Connection con = null;
		boolean cliente = false;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			cliente = empleadoDAO.updatePassword(con, PASSWORD_ENCRYPTOR.encryptPassword(password), id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;
	}

	public boolean updatePassword(Employee empl)
			throws DataException{

		Connection con = null;
		boolean em = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);

			if(empl.getPassword().length()>=8 && (empl.getPassword().length()<=16)){
				empl.setPassword(PASSWORD_ENCRYPTOR.encryptPassword(empl.getPassword()));
			}else {
				empl = empleadoDAO.findBy(con, empl.getId());
			}

			em = empleadoDAO.update(con, empl);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}



	public Long registrar(Employee empl) 
			throws DataException, ServiceException{

		Connection con = null;
		Long id = null;
		boolean commit = false;

		empl.setPassword(PASSWORD_ENCRYPTOR.
				encryptPassword(empl.getPassword()));

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = empleadoDAO.create(con, empl);

			mailService.enviar(empl.getEmail(),"Bienvenido a nuestro equipo de trabajo", "Estamos encantados de que formes parte de "
					+ "nuestro equipo de trabajo para poder alcanzar el máximo nivel de productividad y satisfacción de nuestros clientes. ");

			commit = true;
		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return id;
	}
}
