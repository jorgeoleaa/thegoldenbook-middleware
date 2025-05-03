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

public class EmpleadoDAOImpl implements EmployeeDAO {

	private static Logger logger = LogManager.getLogger(EmpleadoDAOImpl.class); 
	private AddressDAO direccionDAO = null;

	public EmpleadoDAOImpl() {
		direccionDAO = new AddressDAOImpl();
	}

	public Employee findBy(Connection con, Long id)
			throws DataException{

		Employee em = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try { 

			StringBuilder query = new StringBuilder(" SELECT E.ID, E.NOMBRE, E.APELLIDO1, E.APELLIDO2, E.DNI_NIE, E.TELEFONO, E.EMAIL, E.PASSWORD, E.tipo_empleado_id, t.nombre ")
					.append(" FROM EMPLEADO E ")
					.append(" inner join tipo_empleado t on t.id = e.tipo_empleado_id ")
					.append(" WHERE E.ID = ? ");


			logger.info(query.toString());


			pst = con.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			pst.setLong(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				em = loadNext(rs, con);
			}

		} catch (SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return em;
	}

	public Results<Employee> findAll(Connection con, int pos, int pageSize) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Results<Employee> empleados = new Results<Employee>();

		try {
			StringBuilder query = new StringBuilder(" SELECT E.ID, E.NOMBRE, E.APELLIDO1, E.APELLIDO2, E.DNI_NIE, E.TELEFONO, E.EMAIL, E.PASSWORD, E.tipo_empleado_id, t.nombre ")
					.append(" FROM EMPLEADO E ")
					.append(" inner join tipo_empleado t on t.id = e.tipo_empleado_id ")
					.append(" ORDER BY E.NOMBRE ASC");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					empleados.getPage().add(loadNext(rs, con));
					count++;
				}while (count<pageSize && rs.next());
			}
			empleados.setTotal(JDBCUtils.getTotalRows(rs));

		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return empleados;
	}

	public Long create(Connection con, Employee em) 
			throws DataException{

		ResultSet rs = null;
		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(" INSERT INTO EMPLEADO (NOMBRE, APELLIDO1, APELLIDO2, DNI_NIE, TELEFONO, EMAIL, PASSWORD, TIPO_EMPLEADO_ID)"
					+" VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


			logger.info(pst);



			int i = 1;

			pst.setString(i++, em.getNombre());
			pst.setString(i++, em.getApellido1());
			JDBCUtils.setNullable(pst, i++, em.getApellido2());						 
			pst.setString(i++, em.getDniNie());
			pst.setString(i++, em.getTelefono());
			pst.setString(i++, em.getEmail());
			pst.setString(i++, em.getPassword());
			pst.setInt(i++, em.getTipo_empleado_id());
												
		

			int insertedRows = pst.executeUpdate();

			if(insertedRows == 0) {

			}

			rs = pst.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				em.setId(id);
				em.getDireccion().setEmpleadoId(id);
				direccionDAO.create(con, em.getDireccion());
				return id;

			} 


		} catch(SQLException e) {
			logger.error("Empleado: "+em, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return null;
	}

	public boolean updatePassword(Connection con, String password, Long id) throws DataException {

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement("UPDATE EMPLEADO SET PASSWORD = ? "
					+ " WHERE ID = ? ");

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
			pst = con.prepareStatement(" UPDATE EMPLEADO SET NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, DNI_NIE = ?, TELEFONO = ?, EMAIL = ?, TIPO_EMPLEADO_ID = ? "
					+" WHERE ID = ? ");


			logger.info(pst);

			int i = 1;
			pst.setString(i++, em.getNombre());
			pst.setString(i++, em.getApellido1());
			pst.setString(i++, em.getApellido2());
			pst.setString(i++, em.getDniNie());
			pst.setString(i++, em.getTelefono());
			pst.setString(i++, em.getEmail());
			pst.setInt(i++, em.getTipo_empleado_id());
			pst.setLong(i++, em.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows != 1) {
				return false;
			}

		} catch(SQLException e) {
			logger.error("Empleado: "+em, e);
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

			direccionDAO.deleteByEmpleado(con, id);

			pst = con.prepareStatement(" DELETE FROM EMPLEADO WHERE ID = ? ");

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

	protected Employee loadNext (ResultSet rs, Connection con) throws SQLException, DataException{

		Employee em = new Employee();
		int i = 1;
		em.setId(rs.getLong(i++));
		em.setNombre(rs.getString(i++));
		em.setApellido1(rs.getString(i++));
		em.setApellido2(rs.getString(i++));
		em.setDniNie(rs.getString(i++));
		em.setTelefono(rs.getString(i++));
		em.setEmail(rs.getString(i++));
		em.setPassword(rs.getString(i++));
		em.setTipo_empleado_id(rs.getInt(i++));
		em.setTipo_empleado_nombre(rs.getString(i++));
		em.setDireccion(direccionDAO.findByEmpleadoId(con, em.getId()));

		return em;
	}

}
