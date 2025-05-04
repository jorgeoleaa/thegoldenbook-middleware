package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.FormatDAO;
import com.thegoldenbook.model.Format;
import com.thegoldenbook.util.JDBCUtils;

public class FormatoDAOImpl implements FormatDAO{

	private static Logger logger = LogManager.getLogger(TematicaDAOImpl.class);
	
	public FormatoDAOImpl() {
		
	}
	
	public List<Format> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Format> resultados = new ArrayList<Format>();
		
		try {
			
			StringBuilder query = new StringBuilder (" SELECT F.ID, FI.NOMBRE ")
					.append(" FROM FORMATO F ")
					.append(" INNER JOIN FORMATO_IDIOMA FI ON FI.FORMATO_ID = F.ID")
					.append(" INNER JOIN IDIOMA I ON I.ID = FI.IDIOMA_ID")
					.append(" WHERE I.LOCALE = ?");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
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
	
	protected Format loadNext (ResultSet rs) throws SQLException{
		
		Format f = new Format();
		int i = 1;
		
		f.setId(rs.getInt(i++));
		f.setNombre(rs.getString(i++));
		
		return f;
	}

	
}
