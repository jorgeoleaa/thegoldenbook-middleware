package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.EmployeeDAO;
import com.thegoldenbook.dao.impl.EmployeeDAOImpl;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.EmployeeService;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.ServiceException;
import com.thegoldenbook.util.JDBCUtils;


public class EmployeeServiceImpl implements EmployeeService{

	public static final StrongPasswordEncryptor PASSWORD_ENCRYPTOR 
	= new StrongPasswordEncryptor();

	private static Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

	private EmployeeDAO employeeDAO = null;
	private MailService mailService = null;

	public EmployeeServiceImpl() {
		employeeDAO = new EmployeeDAOImpl();
		mailService = new MailServiceImpl();
	}

	public Results<Employee> findAll(int pos, int pageSize, String locale) throws DataException {

		Connection con = null;
		Boolean commit = false;
		Results<Employee> employees = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			employees = employeeDAO.findAll(con, pos, pageSize, locale);
			commit = true;

		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return employees;
	}

	public Employee findBy(Long id, String locale) 
			throws DataException, ServiceException{

		Connection con = null;
		Employee em = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			em = employeeDAO.findBy(con, id, locale);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}

	public Employee authenticate(Long id, String password, String locale) throws DataException{

		Employee employee = null;
		Connection con = null;
		boolean commit = false;


		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			employee = employeeDAO.findBy(con, id, locale);

			if(employee == null) {
				return null;
			}

			if(!PASSWORD_ENCRYPTOR.checkPassword(password, employee.getPassword())) {
				employee = null;
			} 
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return employee;
	}

	public boolean delete(Long id, String locale) 
			throws DataException, ServiceException{

		Connection con = null;
		boolean em = false;
		Employee employee = null;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			employee = employeeDAO.findBy(con, id, locale);
			em = employeeDAO.delete(con, id);
			mailService.send(employee.getEmail(), "Notice of Account Deletion", "We are sending you this message to inform you that, since you are no longer part of our team of employees,"
					+ " your employee account has been deleted."
					+ " We appreciate your services during this time. ");

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
			em = employeeDAO.update(con, empl);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}

	public boolean updatePassword(String password, Long id, String locale) throws DataException{
		Connection con = null;
		boolean cliente = false;
		boolean commit = false;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			cliente = employeeDAO.updatePassword(con, PASSWORD_ENCRYPTOR.encryptPassword(password), id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return cliente;
	}

	public boolean updatePassword(Employee empl, String locale)
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
				empl = employeeDAO.findBy(con, empl.getId(), locale);
			}

			em = employeeDAO.update(con, empl);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(con, commit);
		}
		return em;
	}



	public Long register(Employee empl) 
			throws DataException, ServiceException{

		Connection con = null;
		Long id = null;
		boolean commit = false;

		empl.setPassword(PASSWORD_ENCRYPTOR.
				encryptPassword(empl.getPassword()));

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			id = employeeDAO.create(con, empl);

			mailService.send(empl.getEmail(), "Welcome to our team", 
				    "We are delighted to have you as part of our team, working together to achieve the highest level of productivity and customer satisfaction.");

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
