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
import com.thegoldenbook.dao.EmployeeTypeDAO;
import com.thegoldenbook.model.EmployeeType;
import com.thegoldenbook.util.JDBCUtils;

public class EmployeeTypeDAOImpl implements EmployeeTypeDAO{
	
	private static Logger logger = LogManager.getLogger();
	
	@Override
	public List<EmployeeType> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<EmployeeType> types = new ArrayList<EmployeeType>();
		
		try {
			
			StringBuilder query = new StringBuilder(" select et.id, etl.name ")
					.append(" from employee_type et ")
					.append(" inner join employee_type_language etl on etl.employee_type_id = et.id ")
					.append(" inner join language l on l.id = etl.language_id ")
					.append(" where l.locale = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				types.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		
		return types;
	}
	
	protected EmployeeType loadNext(ResultSet rs) throws SQLException{
		
		EmployeeType tipo = new EmployeeType();
		int i = 1;
		
		tipo.setId(rs.getInt(i++));
		tipo.setName(rs.getString(i++));
		
		return tipo;
	}

}
