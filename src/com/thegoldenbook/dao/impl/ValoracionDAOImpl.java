package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.ValoracionDAO;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ValoracionCriteria;
import com.thegoldenbook.util.JDBCUtils;
import com.thegoldenbook.util.SQLUtils;

public class ValoracionDAOImpl implements ValoracionDAO{

	private static Logger logger = LogManager.getLogger(ValoracionDAOImpl.class);

	public ValoracionDAOImpl() {

	}

	public Results<Review> findByValoracionCriteria(Connection con, ValoracionCriteria v, int pos, int pageSize) throws DataException{

		Results<Review> resultados = new Results<Review>();
		List<String> condiciones = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT V.CLIENTE_ID, C.NICKNAME, C.NOMBRE AS CLIENTE_NOMBRE, C.APELLIDO1 AS APELLIDO1_CLIENTE, C.APELLIDO2 AS APELLIDO2_CLIENTE, V.LIBRO_ID AS ID_LIBRO, L.NOMBRE AS LIBRO, A.ID AS AUTOR_ID, A.NOMBRE AS AUTOR_NOMBRE, A.APELLIDO1 AS AUTOR_APELLIDO1, A.APELLIDO2 AS AUTOR_APELLIDO2, V.NUMERO_ESTRELLAS, V.ASUNTO, V.CUERPO, V.FECHA_PUBLICACION")
					.append(" FROM CLIENTE_LIBRO V ")
					.append(" INNER JOIN CLIENTE C ON C.ID = V.CLIENTE_ID ")
					.append(" INNER JOIN LIBRO L ON L.ID = V.LIBRO_ID ")
					.append(" INNER JOIN LIBRO_AUTOR LA ON LA.LIBRO_ID = V.LIBRO_ID ")
					.append(" INNER JOIN AUTOR A ON A.ID = LA.AUTOR_ID ");


			if (v.getClienteId() != null) { 
				condiciones.add(" V.CLIENTE_ID = ? ");
			}

			if(v.getLibroId() != null) {
				condiciones.add(" V.LIBRO_ID = ? ");
			}

			if(v.getFechaDesde()!= null) {
				condiciones.add(" V.FECHA_PUBLICACION >= ? ");
			}

			if(v.getFechaHasta() != null) {
				condiciones.add(" V.FECHA_PUBLICACION <= ? ");
			}

			if(v.getPalabra()!= null) {
				condiciones.add(" V.ASUNTO LIKE ? OR V.CUERPO LIKE ? ");
			}

			if (!condiciones.isEmpty()) {
				query.append(" WHERE ");
				query.append(String.join(" AND ", condiciones));
			}

			query.append(" ORDER BY ").append(v.getOrderBy()).append(v.getAscDesc() ? " ASC " : " DESC ");

			String sql = query.toString();

			pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			if(v.getClienteId() != null) {
				pst.setLong(i++, v.getClienteId());
			}

			if (v.getLibroId() != null) {
				pst.setLong(i++, v.getLibroId());
			}

			if (v.getFechaDesde() != null) {
				pst.setDate(i++, new java.sql.Date(v.getFechaDesde().getTime()));
			}

			if (v.getFechaHasta() != null) {
				pst.setDate(i++, new java.sql.Date(v.getFechaHasta().getTime()));
			}


			if (v.getPalabra() != null) {
				pst.setString(i++, SQLUtils.envolverLike(v.getPalabra()));
				pst.setString(i++, SQLUtils.envolverLike(v.getPalabra()));

			}

			logger.info("Ejecutando query: "+query);
			rs = pst.executeQuery();

			int count = 0;

			if((pos>=1) && rs.absolute(pos)) {

				do {
					resultados.getPage().add(loadNext(rs));

				}while(count<pageSize && rs.next());

			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));


		}catch(SQLException e){
			logger.error("ValoracionCriteria: "+v ,e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}

	public Results<Review> findByCliente(Connection con, Long clienteId, int pos, int pageSize) throws DataException{

		Results<Review> resultados = new Results<Review>();
		ResultSet rs = null;
		PreparedStatement pst = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT V.CLIENTE_ID, C.NICKNAME, C.NOMBRE AS CLIENTE_NOMBRE, C.APELLIDO1 AS APELLIDO1_CLIENTE, C.APELLIDO2 AS APELLIDO2_CLIENTE, V.LIBRO_ID AS ID_LIBRO, L.NOMBRE AS LIBRO, A.ID AS AUTOR_ID, A.NOMBRE AS AUTOR_NOMBRE, A.APELLIDO1 AS AUTOR_APELLIDO1, A.APELLIDO2 AS AUTOR_APELLIDO2, V.NUMERO_ESTRELLAS, V.ASUNTO, V.CUERPO, V.FECHA_PUBLICACION")
					.append(" FROM CLIENTE_LIBRO V ")
					.append(" INNER JOIN CLIENTE C ON C.ID = V.CLIENTE_ID ")
					.append(" INNER JOIN LIBRO L ON L.ID = V.LIBRO_ID ")
					.append(" INNER JOIN LIBRO_AUTOR LA ON LA.LIBRO_ID = V.LIBRO_ID ")
					.append(" INNER JOIN AUTOR A ON A.ID = LA.AUTOR_ID ")
					.append(" WHERE V.CLIENTE_ID = ? ")
					.append(" ORDER BY V.FECHA_PUBLICACION DESC");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, clienteId);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs));
					count++;
				}while (count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		} catch(SQLException e) {
			logger.error("ClienteID: "+clienteId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}


	public Results<Review> findByLibro(Connection con, Long libroId, int pos, int pageSize) throws DataException{

		Results<Review> resultados = new Results<Review>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT V.CLIENTE_ID, C.NICKNAME, C.NOMBRE AS CLIENTE_NOMBRE, C.APELLIDO1 AS APELLIDO1_CLIENTE, C.APELLIDO2 AS APELLIDO2_CLIENTE, V.LIBRO_ID AS ID_LIBRO, L.NOMBRE AS LIBRO, A.ID AS AUTOR_ID, A.NOMBRE AS AUTOR_NOMBRE, A.APELLIDO1 AS AUTOR_APELLIDO1, A.APELLIDO2 AS AUTOR_APELLIDO2, V.NUMERO_ESTRELLAS, V.ASUNTO, V.CUERPO, V.FECHA_PUBLICACION")
					.append(" FROM CLIENTE_LIBRO V ")
					.append(" INNER JOIN CLIENTE C ON C.ID = V.CLIENTE_ID ")
					.append(" INNER JOIN LIBRO L ON L.ID = V.LIBRO_ID ")
					.append(" INNER JOIN LIBRO_AUTOR LA ON LA.LIBRO_ID = V.LIBRO_ID ")
					.append(" INNER JOIN AUTOR A ON A.ID = LA.AUTOR_ID ")
					.append(" WHERE V.LIBRO_ID = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, libroId);

			rs = pst.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs));
					count++;
				}while (count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		} catch(SQLException e) {
			logger.error("LibroID: "+libroId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}

	public boolean delete(Connection con, Long clienteId, Long libroId) throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement("DELETE FROM CLIENTE_LIBRO WHERE CLIENTE_ID = ? AND LIBRO_ID = ? ");

			int i = 1;
			pst.setLong(i++, clienteId);
			pst.setLong(i++, libroId);

			int deletedRows = pst.executeUpdate();

			if(deletedRows != 1) {
				return false;
			}

		} catch (SQLException e) {
			logger.error("ClienteID: "+clienteId, "LibroID: "+libroId, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public boolean update(Connection con, Review v) throws DataException{

		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(" UPDATE CLIENTE_LIBRO SET NUMERO_ESTRELLAS = ?, ASUNTO = ?, CUERPO = ? "
					+ "WHERE CLIENTE_ID = ? AND LIBRO_ID = ? ");

			int i = 1;

			pst.setDouble(i++, v.getNumeroEstrellas());
			pst.setString(i++, v.getAsunto());
			pst.setString(i++, v.getCuerpo());
			pst.setLong(i++, v.getClienteId());
			pst.setLong(i++, v.getLibroId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows == 0) {
				return false;

			}

		} catch(SQLException e) {
			logger.error("ValoracionDTO: "+v, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(null, null);
		}
		return true;
	}

	public void create(Connection con, Review v) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement("INSERT INTO CLIENTE_LIBRO (CLIENTE_ID, LIBRO_ID, NUMERO_ESTRELLAS, ASUNTO, CUERPO, FECHA_PUBLICACION) "
					+ " VALUES(?,?,?,?,?,?)");

			int i = 1;
			pst.setLong(i++, v.getClienteId());
			pst.setLong(i++, v.getLibroId());
			pst.setDouble(i++, v.getNumeroEstrellas());
			pst.setString(i++, v.getAsunto());
			pst.setString(i++, v.getCuerpo());
			pst.setTimestamp(i, new Timestamp(v.getFechaPublicacion().getTime()));

			pst.executeUpdate();


		} catch(SQLException e) {
			logger.error("ValoracionDTO: "+v, e);
			throw new DataException(e);

		}finally {
			JDBCUtils.close(pst);
		}
	}

	protected Review loadNext (ResultSet rs) throws SQLException {

		Review v = new Review();
		int i = 1;

		v.setClienteId(rs.getLong(i++));
		v.setNickname(rs.getString(i++));
		v.setNombreCliente(rs.getString(i++));
		v.setApellido1Cliente(rs.getString(i++));
		v.setApellido2Cliente(rs.getString(i++));
		v.setLibroId(rs.getLong(i++));
		v.setNombreLibro(rs.getString(i++));
		v.setAutorId(rs.getLong(i++));
		v.setNombreAutor(rs.getString(i++));
		v.setApellido1Autor(rs.getString(i++));
		v.setApellido2Autor(rs.getString(i++));
		v.setNumeroEstrellas(rs.getDouble(i++));
		v.setAsunto(rs.getString(i++));
		v.setCuerpo(rs.getString(i++));
		v.setFechaPublicacion(rs.getTimestamp(i++));

		return v;
	}




	public Review findByValoracion(Connection con, Long clienteId, Long libroId) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Review v = null;
		try {
			StringBuilder query = new StringBuilder(" SELECT V.CLIENTE_ID, C.NICKNAME, C.NOMBRE AS CLIENTE_NOMBRE, C.APELLIDO1 AS APELLIDO1_CLIENTE, C.APELLIDO2 AS APELLIDO2_CLIENTE, V.LIBRO_ID AS ID_LIBRO, L.NOMBRE AS LIBRO, A.ID AS AUTOR_ID, A.NOMBRE AS AUTOR_NOMBRE, A.APELLIDO1 AS AUTOR_APELLIDO1, A.APELLIDO2 AS AUTOR_APELLIDO2, V.NUMERO_ESTRELLAS, V.ASUNTO, V.CUERPO, V.FECHA_PUBLICACION")
					.append(" FROM CLIENTE_LIBRO V ")
					.append(" INNER JOIN CLIENTE C ON C.ID = V.CLIENTE_ID ")
					.append(" INNER JOIN LIBRO L ON L.ID = V.LIBRO_ID ")
					.append(" INNER JOIN LIBRO_AUTOR LA ON LA.LIBRO_ID = V.LIBRO_ID ")
					.append(" INNER JOIN AUTOR A ON A.ID = LA.AUTOR_ID ")
					.append(" WHERE V.CLIENTE_ID = ? AND V.LIBRO_ID = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, clienteId);
			pst.setLong(i++, libroId);

			rs = pst.executeQuery();

			if(rs.next()) {
				v = loadNext(rs);
			}

		}catch(SQLException e) {
			logger.error("ClienteID: "+clienteId, "LibroID: "+libroId, e); 
			throw new DataException(e);
		}
		return v;
	}
}
