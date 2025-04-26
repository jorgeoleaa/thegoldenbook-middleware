package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.DireccionDAO;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.util.JDBCUtils;

public class DireccionDAOImpl implements DireccionDAO {

	private static Logger logger = LogManager.getLogger(DireccionDAOImpl.class);
	
	public DireccionDAOImpl() {
		
	}
	

	public Address findByEmpleadoId(Connection con, Long empleadoId) throws DataException{

		Address d = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {

			StringBuilder query = new StringBuilder(" SELECT D.ID AS ID_DIRECCION, D.NOMBRE_VIA, D.DIR_VIA, D.CLIENTE_ID, D.EMPLEADO_ID, D.LOCALIDAD_ID, L.NOMBRE AS NOMBRE_LOCALIDAD, PR.ID AS ID_PROVINCIA, PR.NOMBRE AS NOMBRE_PROVINCIA, PA.ID AS ID_PAIS, PA.NOMBRE AS NOMBRE_PAIS ")
					.append(" FROM DIRECCION D ")
					.append(" INNER JOIN LOCALIDAD L ON L.ID = D.LOCALIDAD_ID ")
					.append(" INNER JOIN PROVINCIA PR ON PR.ID = L.PROVINCIA_ID ")
					.append(" INNER JOIN PAIS PA ON PA.ID = PR.PAIS_ID ")
					.append(" WHERE D.EMPLEADO_ID = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, empleadoId);

			rs = pst.executeQuery();

			if(rs.next()) {
				d = loadNext(rs);
			}

		}catch (SQLException e) {
			logger.error("EmpleadoID: "+empleadoId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return d;
	}



	public boolean delete(Connection con, Long id) throws DataException{

		PreparedStatement pst = null;
		
		try {

			pst = con.prepareStatement(" DELETE FROM DIRECCION WHERE ID = ? ");

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

	
	public boolean update(Connection con, Address d) throws DataException{

		PreparedStatement pst = null;
		
		try {

			pst = con.prepareStatement(" UPDATE DIRECCION SET NOMBRE_VIA = ?, DIR_VIA = ?, LOCALIDAD_ID = ?, CLIENTE_ID = ?, EMPLEADO_ID = ? "
					+" WHERE ID = ?");
			
			int i = 1;
			pst.setString(i++, d.getNombreVia());
			pst.setString(i++, d.getDirVia());
			pst.setInt(i++, d.getLocalidadId());
			JDBCUtils.setNullable(pst, i++, d.getClienteId());
			JDBCUtils.setNullable(pst, i++, d.getEmpleadoId());
			pst.setLong(i++, d.getId());
			
			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;
			}

		} catch (SQLException e) {
			logger.error("DireccionDTO: "+d, e);
			throw new DataException (e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}

	
	public Long create(Connection con, Address d) throws DataException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			pst = con.prepareStatement(" INSERT INTO DIRECCION (NOMBRE_VIA, DIR_VIA, LOCALIDAD_ID, CLIENTE_ID, EMPLEADO_ID)"
													+ " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			JDBCUtils.setNullable(pst, i++, d.getNombreVia());
			JDBCUtils.setNullable(pst, i++, d.getDirVia());
			JDBCUtils.setNullable(pst, i++, d.getLocalidadId());				
			JDBCUtils.setNullable(pst, i++, d.getClienteId());
			JDBCUtils.setNullable(pst, i++, d.getEmpleadoId());
			
			int insertedRows = pst.executeUpdate();
			
			if(insertedRows != 1) {
				
			}
			
			rs = pst.getGeneratedKeys();
			
			if(rs.next()) {
				Long id = rs.getLong(1);
				d.setId(id);
				return id;
			}
			
		} catch(SQLException e) {
			logger.error("DireccionDTO: "+d, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return null;
	}
	
	public boolean deleteByEmpleado(Connection con, Long empleadoId) throws DataException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement(" DELETE FROM DIRECCION WHERE EMPLEADO_ID = ? ");
			
			int i = 1;
			pst.setLong(i++, empleadoId);
			
			int deletedRows = pst.executeUpdate();
			
			if(deletedRows == 0) {
				return false;
			}
			
		} catch(SQLException e) {
			logger.error("EmpleadoID: "+empleadoId, e);
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
		d.setNombreVia(rs.getString(i++));
		d.setDirVia(rs.getString(i++));
		d.setClienteId(JDBCUtils.getNullableLong(rs, i++));
		d.setEmpleadoId(JDBCUtils.getNullableLong(rs, i++));
		d.setLocalidadId(rs.getInt(i++));
		d.setLocalidadNombre(rs.getString(i++));
		d.setProvinciaId(rs.getInt(i++));
		d.setProvinciaNombre(rs.getString(i++));
		d.setPaisId(rs.getInt(i++));
		d.setPaisNombre(rs.getString(i++));
		
		return d;
	}
}
