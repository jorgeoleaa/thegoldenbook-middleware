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

import com.thegoldenbook.dao.AutorDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LibroDAO;
import com.thegoldenbook.dao.TematicaDAO;
import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.service.LibroCriteria;
import com.thegoldenbook.util.JDBCUtils;
import com.thegoldenbook.util.SQLUtils;

public class LibroDAOImpl implements LibroDAO {

	private static Logger logger = LogManager.getLogger(LibroDAOImpl.class);
	private AutorDAO autorDAO = null;
	private TematicaDAO tematicaDAO = null;

	public LibroDAOImpl() {
		autorDAO = new AutorDAOImpl();
		tematicaDAO = new TematicaDAOImpl();
	}

	public Book findByLibro(Connection con, String locale, Long id) throws DataException {

		PreparedStatement pst = null;
		ResultSet rs = null;
		Book l = null;

		try {
			StringBuilder query = new StringBuilder("SELECT L.ID, L.ISBN,L.NOMBRE AS TITULO, L.FECHA_PUBLICACION, L.SINOPSIS, L.VALORACION_MEDIA, L.UNIDADES, L.PRECIO, GLI.GENERO_LITERARIO_ID ,GLI.NOMBRE AS GENERO_LITERARIO, CEI.CLASIFICACION_EDAD_ID ,"
					+ "CEI.NOMBRE AS CLASIFICACION_EDAD, FI.FORMATO_ID, FI.NOMBRE AS FORMATO, L.IDIOMA_ID, II.NOMBRE AS IDIOMA ")
					.append("FROM LIBRO L ")
					.append("INNER JOIN IDIOMA I ON I.ID = L.IDIOMA_ID ")
					.append("INNER JOIN IDIOMA I2 ON I2.LOCALE = ? ")
					.append("INNER JOIN IDIOMA_IDIOMA II ON II.IDIOMA_ID = I.ID AND II.IDIOMA_ID1 = I2.ID ")
					.append("INNER JOIN GENERO_LITERARIO GL ON GL.ID = L.GENERO_LITERARIO_ID ")
					.append("INNER JOIN GENERO_LITERARIO_IDIOMA GLI ON GL.ID = GLI.GENERO_LITERARIO_ID AND GLI.IDIOMA_ID = I2.ID ")
					.append("INNER JOIN CLASIFICACION_EDAD CE ON CE.ID = L.CLASIFICACION_EDAD_ID ")
					.append("INNER JOIN CLASIFICACION_EDAD_IDIOMA CEI ON CE.ID = CEI.CLASIFICACION_EDAD_ID AND CEI.IDIOMA_ID = I2.ID ")
					.append("INNER JOIN FORMATO F ON F.ID = L.FORMATO_ID ")
					.append("INNER JOIN FORMATO_IDIOMA FI ON F.ID = FI.FORMATO_ID AND FI.IDIOMA_ID = I2.ID ")
					.append("WHERE L.ID = ? ");
					


			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, id);

			rs = pst.executeQuery();
			if (rs.next()) {
				l = loadNext(rs, locale, con);
			}

		} catch (SQLException e) {
			logger.error("ID:" + id, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pst, rs);
		}
		return l;
	}

	public Results<Book> findByCriteria(Connection con, LibroCriteria l, int pos, int pageSize) throws DataException {

		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		Results<Book> resultados = new Results<Book>();
		List<String> condiciones = new ArrayList<String>();

		try {

			StringBuilder query = new StringBuilder(
					"SELECT L.ID, L.ISBN,L.NOMBRE AS TITULO, L.FECHA_PUBLICACION, L.SINOPSIS, L.VALORACION_MEDIA, L.UNIDADES,"
					+ " L.PRECIO, GLI.GENERO_LITERARIO_ID ,GLI.NOMBRE AS GENERO_LITERARIO, CEI.CLASIFICACION_EDAD_ID ,CEI.NOMBRE AS CLASIFICACION_EDAD, FI.FORMATO_ID, FI.NOMBRE AS FORMATO, II.IDIOMA_ID, II.NOMBRE AS IDIOMA "
					+ "FROM LIBRO L "
					+ "INNER JOIN IDIOMA I "
					+ "    ON I.ID = L.IDIOMA_ID "
					+ "INNER JOIN IDIOMA I2 "
					+ "	ON I2.LOCALE = ? "
					+ "INNER JOIN IDIOMA_IDIOMA II "
					+ "    ON II.IDIOMA_ID = I.ID "
					+ "    AND II.IDIOMA_ID1 = I2.ID "
					+ "INNER JOIN GENERO_LITERARIO GL "
					+ "    ON GL.ID = L.GENERO_LITERARIO_ID "
					+ "INNER JOIN GENERO_LITERARIO_IDIOMA GLI "
					+ "    ON GL.ID = GLI.GENERO_LITERARIO_ID "
					+ "    AND GLI.IDIOMA_ID = I2.ID "
					+ "INNER JOIN CLASIFICACION_EDAD CE "
					+ "    ON CE.ID = L.CLASIFICACION_EDAD_ID "
					+ "INNER JOIN CLASIFICACION_EDAD_IDIOMA CEI "
					+ "    ON CE.ID = CEI.CLASIFICACION_EDAD_ID "
					+ "    AND CEI.IDIOMA_ID = I2.ID "
					+ "INNER JOIN FORMATO F "
					+ "    ON F.ID = L.FORMATO_ID "
					+ "INNER JOIN FORMATO_IDIOMA FI "
					+ "    ON F.ID = FI.FORMATO_ID "
					+ "    AND FI.IDIOMA_ID = I2.ID ");

			
			
			if (l.getId() != null) {
				condiciones.add(" L.ID = ? ");
			} else if (l.getIsbn() != null) {
				condiciones.add(" L.ISBN like ? ");
			}

			else {
				if (l.getNombre() != null) {
					condiciones.add(" L.NOMBRE LIKE ? ");
				}

				if (l.getDesdeFecha() != null) {
					condiciones.add(" L.FECHA_PUBLICACION >= ? ");
				}

				if (l.getHastaFecha() != null) {
					condiciones.add(" L.FECHA_PUBLICACION <= ? ");
				}

				if (l.getUnidadesDesde() != null) {
					condiciones.add(" L.UNIDADES >= ? ");
				}

				if (l.getUnidadesHasta() != null) {
					condiciones.add(" L.UNIDADES <= ? ");
				}

				if (l.getDesdePrecio() != null) {
					condiciones.add(" L.PRECIO >= ? ");
				}

				if (l.getHastaPrecio() != null) {
					condiciones.add(" L.PRECIO <= ? ");
				}

				if (l.getGeneroLiterarioId() != null) {
					condiciones.add(" L.GENERO_LITERARIO_ID = ? ");
				}

				if (l.getClasificacionEdadId() != null) {
					condiciones.add(" L.CLASIFICACION_EDAD_ID = ? ");
				}

				if(l.getIdiomaId() != null) {
					condiciones.add(" L.IDIOMA_ID = ? ");
				}

				if(l.getFormatoId() != null) {
					condiciones.add(" L.FORMATO_ID = ? ");
				}
			}
			if (!condiciones.isEmpty()) {

				query.append(" WHERE ");
				query.append(String.join(" AND ", condiciones));
			}

			query.append(" ORDER BY ").append(l.getOrderBy()).append(l.getAscDesc() ? " ASC " : " DESC ");


			String sql = query.toString();

			preparedStatement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, l.getLocale());

			if (l.getId() != null) {
				preparedStatement.setLong(i++, l.getId());
			}

			else if (l.getIsbn() != null) {
				preparedStatement.setString(i++,  SQLUtils.envolverLike(l.getIsbn()));
			} else {

				if (l.getNombre() != null) {
					preparedStatement.setString(i++, SQLUtils.envolverLike(l.getNombre()));
				}

				if (l.getDesdeFecha() != null) {
					preparedStatement.setDate(i++, new java.sql.Date(l.getDesdeFecha().getTime()));
				}

				if (l.getHastaFecha() != null) {
					preparedStatement.setDate(i++, new java.sql.Date(l.getHastaFecha().getTime()));
				}

				if(l.getUnidadesDesde() != null) {
					preparedStatement.setInt(i++, l.getUnidadesDesde());
				}

				if(l.getUnidadesHasta() != null) {
					preparedStatement.setInt(i++, l.getUnidadesHasta());
				}

				if (l.getDesdePrecio() != null) {
					preparedStatement.setDouble(i++, l.getDesdePrecio());
				}

				if (l.getHastaPrecio() != null) {
					preparedStatement.setDouble(i++, l.getHastaPrecio());
				}

				if (l.getGeneroLiterarioId() != null) {
					preparedStatement.setInt(i++, l.getGeneroLiterarioId());
				}

				if (l.getClasificacionEdadId() != null) {
					preparedStatement.setInt(i++, l.getClasificacionEdadId());
				}

				if(l.getIdiomaId() != null) {
					preparedStatement.setInt(i++, l.getIdiomaId());
				}

				if(l.getFormatoId() != null) {
					preparedStatement.setInt(i++, l.getFormatoId());
				}
			}

			rs = preparedStatement.executeQuery();

			int count = 0;
			if((pos>=1) && rs.absolute(pos)) {
				do {
					resultados.getPage().add(loadNext(rs, l.getLocale(), con));
					count++;
				}while (count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		} catch (SQLException e) {
			logger.error("Libro Criteria: " + l, e);
			throw new DataException(e);

		} finally {
			JDBCUtils.close(preparedStatement, rs);
		}

		return resultados;
	}

	public Long create(Connection con, String locale,Book l) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			preparedStatement = con.prepareStatement(
					" INSERT INTO LIBRO(ISBN, NOMBRE, FECHA_PUBLICACION, SINOPSIS, VALORACION_MEDIA, UNIDADES, PRECIO, GENERO_LITERARIO_ID, CLASIFICACION_EDAD_ID, IDIOMA_ID, FORMATO_ID) "
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			JDBCUtils.setNullable(preparedStatement, i++, l.getIsbn());
			preparedStatement.setString(i++, l.getNombre());
			JDBCUtils.setNullable(preparedStatement, i++, new java.sql.Date(l.getFechaPublicacion().getTime()));
			preparedStatement.setString(i++, l.getSinopsis());
			JDBCUtils.setNullable(preparedStatement, i++, l.getValoracionMedia());
			preparedStatement.setInt(i++, l.getUnidades());
			preparedStatement.setDouble(i++, l.getPrecio());
			preparedStatement.setInt(i++, l.getGeneroLiterarioId());
			preparedStatement.setInt(i++, l.getClasificacionEdadId());
			preparedStatement.setInt(i++, l.getIdiomaId());
			preparedStatement.setInt(i++, l.getFormatoId());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows != 1) {
				// throw new ...Exception
			}

			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				Long id = rs.getLong(1);
				l.setId(id);

				List<Long> ids = new ArrayList<Long>();
				for(Author a : l.getAutores()) {
					ids.add(a.getId());
				}
				asignarAutor(con, id, ids);

				List<Integer> idsT = new ArrayList<Integer>();
				for (Subject t : l.getTematicas()) {
					idsT.add(t.getId());
				}
				asignarTematica(con, locale, id, idsT);

				return id;
			}

		} catch (SQLException e) {
			logger.error("LibroDTO: " + l, e);
			throw new DataException(e);

		} finally {
			JDBCUtils.close(preparedStatement, rs);
		}

		return null;
	}

	
	public boolean update(Connection con, Book l) throws DataException {

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(
					"UPDATE LIBRO SET ISBN = ?, NOMBRE = ?, FECHA_PUBLICACION = ?, SINOPSIS = ?, VALORACION_MEDIA = ?, UNIDADES = ?, PRECIO = ?, GENERO_LITERARIO_ID = ?, CLASIFICACION_EDAD_ID = ?, IDIOMA_ID = ?, FORMATO_ID = ? "
							+ " WHERE ID = ? ");

			int i = 1;

			JDBCUtils.setNullable(pst, i++, l.getIsbn());
			pst.setString(i++, l.getNombre());
			JDBCUtils.setNullable(pst, i++, new java.sql.Date(l.getFechaPublicacion().getTime()));
			pst.setString(i++, l.getSinopsis());
			JDBCUtils.setNullable(pst, i++, l.getValoracionMedia());
			pst.setInt(i++, l.getUnidades());
			pst.setDouble(i++, l.getPrecio());
			pst.setInt(i++, l.getGeneroLiterarioId());
			pst.setInt(i++, l.getClasificacionEdadId());
			pst.setInt(i++, l.getIdiomaId());
			pst.setInt(i++, l.getFormatoId());
			pst.setLong(i++, l.getId());
			int updatedRows = pst.executeUpdate();

			if (updatedRows != 1) {
				return false;
			}

		} catch (SQLException e) {
			logger.error("LibroDTO: " + l, e);
			throw new DataException(e);

		} finally {
			JDBCUtils.close(pst);
		}

		return true;
	}

	public void asignarAutor(Connection con, Long libroId, List<Long>autoresId) throws DataException {

		PreparedStatement pst = null;
		List<Author> autoresLibro = autorDAO.findByLibro(con, libroId);
		
		
		if(autoresLibro.isEmpty()) {

		}else {
			try {
				pst = con.prepareStatement(" DELETE FROM LIBRO_AUTOR WHERE LIBRO_ID = ? ");

				int i = 1;
				pst.setLong(i++, libroId);

				pst.executeUpdate();

			}catch(SQLException e) {
				logger.error("LibroID: " + libroId , e);
				throw new DataException(e);
			}finally {
				JDBCUtils.close(pst);
			}
		}


		try {
			StringBuilder query = new StringBuilder (" INSERT INTO LIBRO_AUTOR (LIBRO_ID, AUTOR_ID)");
			query.append("VALUES");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", autoresId.size());
			
			pst = con.prepareStatement(query.toString());
			
			int i = 1;
			for(Long id : autoresId) {
				pst.setLong(i++, libroId);
				pst.setLong(i++, id);
			}
			

			pst.executeUpdate(); 

		}catch (SQLException e) {
			logger.error("LibroID: " + libroId, " AutoresID: "+autoresId  , e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(pst);
		}



	}

	public void asignarTematica(Connection con, String locale, Long libroId, List<Integer>tematicasId) throws DataException {

		PreparedStatement pst = null;
		List<Subject> tematicas = tematicaDAO.findByLibro(con, locale, libroId);

		if(tematicas.isEmpty()) {

		}else {
			try {

				pst = con.prepareStatement(" DELETE FROM LIBRO_TEMATICA WHERE LIBRO_ID = ? ");

				int i = 1;
				pst.setLong(i++, libroId);

				pst.executeUpdate();

			}catch(SQLException e) {
				logger.error("LibroID: "+libroId, e);
				throw new DataException(e);
			}finally {
				JDBCUtils.close(pst);
			}
		}

		try {
			StringBuilder query = new StringBuilder( "INSERT INTO LIBRO_TEMATICA (LIBRO_ID, TEMATICA_ID)");
			query.append("VALUES");
			JDBCUtils.appendMultipleInsertParameters(query, "(?,?)", tematicasId.size());
			
			pst = con.prepareStatement(query.toString());
			
			int i = 1;
			for(Integer id : tematicasId) {
				pst.setLong(i++, libroId);
				pst.setInt(i++, id);
			}
			

			pst.executeUpdate();

		}catch(SQLException e) {
			logger.error("LibroID: "+libroId," TematicaID: "+tematicasId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}

	}
	
	protected Book loadNext(ResultSet rs, String locale, Connection con) throws SQLException, DataException {
		int i;
		Book l = new Book();
		i = 1;
		l = new Book();
		l.setId(rs.getLong(i++));
		l.setIsbn(rs.getString(i++));
		l.setNombre(rs.getString(i++));
		l.setFechaPublicacion(rs.getDate(i++));
		l.setSinopsis(rs.getString(i++));
		l.setValoracionMedia(JDBCUtils.getNullableDouble(rs, i++));
		l.setUnidades(rs.getInt(i++));
		l.setPrecio(rs.getDouble(i++));
		l.setGeneroLiterarioId(rs.getInt(i++));
		l.setGeneroLiterarioNombre(rs.getString(i++));
		l.setClasificacionEdadId(rs.getInt(i++));
		l.setClasificacionEdadNombre(rs.getString(i++));
		l.setFormatoId(rs.getInt(i++));
		l.setFormatoNombre(rs.getString(i++));
		l.setIdiomaId(rs.getInt(i++));
		l.setIdiomaNombre(rs.getString(i++));
		l.setAutores(autorDAO.findByLibro(con, l.getId()));
		l.setTematicas(tematicaDAO.findByLibro(con, locale, l.getId()));


		return l;
	}


}
