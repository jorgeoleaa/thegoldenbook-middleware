package com.pinguela.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.GeneroLiterarioDAO;
import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class GeneroLiterarioDAOImpl implements GeneroLiterarioDAO{
	
	private Logger logger = LogManager.getLogger(GeneroLiterarioDAOImpl.class);
	
	public List<GeneroLiterario> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<GeneroLiterario> generos = new ArrayList<GeneroLiterario>();
		
		try {
			StringBuilder query = new StringBuilder (" SELECT GL.ID, GLI.NOMBRE ")
					.append(" FROM GENERO_LITERARIO GL ")
					.append(" INNER JOIN GENERO_LITERARIO_IDIOMA GLI ON GLI.GENERO_LITERARIO_ID = GL.ID")
					.append(" INNER JOIN IDIOMA I ON I.ID = GLI.IDIOMA_ID")
					.append(" WHERE I.LOCALE = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				generos.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return generos;
	}

	
	protected GeneroLiterario loadNext (ResultSet rs) throws SQLException{
		
		GeneroLiterario genero = new GeneroLiterario();
		
		int i = 1;
		genero.setId(rs.getInt(i++));
		genero.setNombre(rs.getString(i++));
		
		return genero;
	}

}
