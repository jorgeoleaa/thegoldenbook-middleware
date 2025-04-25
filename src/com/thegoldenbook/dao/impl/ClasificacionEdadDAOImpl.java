package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.ClasificacionEdadDAO;
import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.ClasificacionEdad;
import com.thegoldenbook.util.JDBCUtils;

public class ClasificacionEdadDAOImpl implements ClasificacionEdadDAO{

	private static Logger logger = LogManager.getLogger(ClasificacionEdadDAOImpl.class);
	
	public ClasificacionEdadDAOImpl() {
		
	}
	
	public List<ClasificacionEdad> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ClasificacionEdad> resultados = new ArrayList<ClasificacionEdad>();
		
		try {
			
			StringBuilder query = new StringBuilder (" SELECT CE.ID, CEI.NOMBRE, CE.EDAD ")
					.append(" FROM CLASIFICACION_EDAD CE ")
					.append(" INNER JOIN CLASIFICACION_EDAD_IDIOMA CEI ON CEI.CLASIFICACION_EDAD_ID = CE.ID")
					.append(" INNER JOIN IDIOMA I ON I.ID = CEI.IDIOMA_ID")
					.append(" WHERE I.LOCALE = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				resultados.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}
	
	
	protected ClasificacionEdad loadNext (ResultSet rs) throws SQLException{
		
		ClasificacionEdad cd = new ClasificacionEdad();
		int i = 1;
		
		cd.setId(rs.getInt(i++));
		cd.setNombre(rs.getString(i++));
		cd.setEdad(rs.getString(i++));
		
		return cd;
	}
}
