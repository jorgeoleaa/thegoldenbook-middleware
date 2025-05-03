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
import com.thegoldenbook.dao.EmployeeDAO;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.util.JDBCUtils;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger logger = LogManager.getLogger(EmployeeDAOImpl.class); 
	private AddressDAO addressDAO = null;

	public EmployeeDAOImpl() {
		addressDAO = new AddressDAOImpl();
	}

	public Employee findBy(Connection con, Long id, String locale)
			throws DataException{

		Employee em = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try { 

			StringBuilder query = new StringBuilder(" select e.id, e.name, e.last_name, e.second_last_name, e.national_id, e.phone_number, e.email, e.password, et.id, etl.name ")
					.append(" from employee e ")
					.append(" inner join employee_type et on e.employee_type_id = et.id ")
					.append(" inner join employee_type_language etl on etl.employee_type_id = et.id ")
					.append(" inner join language l on l.id = etl.language_id")
					.append(" where e.id = ? and l.locale = ? ");


			logger.info(query.toString());


			pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			pst.setLong(i++, id);
			pst.setString(i++, locale);

			rs = pst.executeQuery();

			if(rs.next()) {
				em = loadNext(rs, con, locale);
			}

		} catch (SQLException e) {
			logger.error("ID: "+id+" locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return em;
	}

	public Results<Employee> findAll(Connection con, int pos, int pageSize, String locale) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Results<Employee> employees = new Results<Employee>();

		try {
			StringBuilder query = new StringBuilder(" select e.id, e.name, e.last_name, e.second_last_name, e.national_id, e.phone_number, e.email, e.password, et.id, etl.name ")
					.append(" from employee e ")
					.append(" inner join employee_type et on e.employee_type_id = et.id ")
					.append(" inner join employee_type_language etl on etl.employee_type_id = et.id ")
					.append(" inner join language l on l.id = etl.language_id ")
					.append(" where l.locale = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					employees.getPage().add(loadNext(rs, con, locale));
					count++;
				}while (count<pageSize && rs.next());
			}
			employees.setTotal(JDBCUtils.getTotalRows(rs));

		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return employees;
	}

	public Long create(Connection con, Employee em) 
			throws DataException{

		ResultSet rs = null;
		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(" insert into employee (name, last_name, second_last_name, national_id, phone_number, email, password, employee_type_id)"
					+" VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


			logger.info(pst);



			int i = 1;

			pst.setString(i++, em.getName());
			pst.setString(i++, em.getLastName());
			JDBCUtils.setNullable(pst, i++, em.getSecondLastName());						 
			pst.setString(i++, em.getNationalId());
			pst.setString(i++, em.getPhoneNumber());
			pst.setString(i++, em.getEmail());
			pst.setString(i++, em.getPassword());
			pst.setInt(i++, em.getEmployeeTypeId());
												
		

			int insertedRows = pst.executeUpdate();

			if(insertedRows == 0) {

			}

			rs = pst.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				em.setId(id);
				em.getAddress().setEmployeeId(id);
				addressDAO.create(con, em.getAddress());
				return id;

			} 


		} catch(SQLException e) {
			logger.error("Employee: "+em, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return null;
	}

	public boolean updatePassword(Connection con, String password, Long id) throws DataException {

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement("update employee set password = ? "
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

	public boolean update(Connection con, Employee em)
			throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(" update empleado set name = ?, last_name = ?, second_last_name = ?, national_id = ?, phone_number = ?, email = ?, employee_type_id = ? "
					+" where id = ? ");


			logger.info(pst);

			int i = 1;
			pst.setString(i++, em.getName());
			pst.setString(i++, em.getLastName());
			pst.setString(i++, em.getSecondLastName());
			pst.setString(i++, em.getNationalId());
			pst.setString(i++, em.getPhoneNumber());
			pst.setString(i++, em.getEmail());
			pst.setInt(i++, em.getEmployeeTypeId());
			pst.setLong(i++, em.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows != 1) {
				return false;
			}

		} catch(SQLException e) {
			logger.error("Employee: "+em, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public boolean delete(Connection con, Long id)
			throws DataException{

		PreparedStatement pst = null;

		try {

			addressDAO.deleteByEmployee(con, id);

			pst = con.prepareStatement(" delete from employee where id = ? ");

			logger.info(pst);

			int i = 1;
			pst.setLong(i++, id);

			int updatedRows = pst.executeUpdate();

			if(updatedRows != 1) {
				return false;
			}


		} catch (SQLException e) {
			logger.error("ID:"+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}	

	protected Employee loadNext (ResultSet rs, Connection con, String locale) throws SQLException, DataException{

		Employee em = new Employee();
		int i = 1;
		em.setId(rs.getLong(i++));
		em.setName(rs.getString(i++));
		em.setLastName(rs.getString(i++));
		em.setSecondLastName(rs.getString(i++));
		em.setNationalId(rs.getString(i++));
		em.setPhoneNumber(rs.getString(i++));
		em.setEmail(rs.getString(i++));
		em.setPassword(rs.getString(i++));
		em.setEmployeeTypeId(rs.getInt(i++));
		em.setEmployeeTypeName(rs.getString(i++));
		em.setAddress(addressDAO.findByEmployeeId(con, em.getId(), locale));

		return em;
	}

}
