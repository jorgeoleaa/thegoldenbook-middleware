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
import com.thegoldenbook.dao.OrderStatusDAO;
import com.thegoldenbook.model.OrderStatus;
import com.thegoldenbook.util.JDBCUtils;

public class OrderStatusDAOImpl implements OrderStatusDAO{

	private static Logger logger = LogManager.getLogger(OrderStatusDAOImpl.class);
	
	public List<OrderStatus> findAll(Connection con, String locale) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderStatus> results = new ArrayList<OrderStatus>();
		
		try {
			
			StringBuilder query = new StringBuilder(" select os.id, osl.name from order_status os ")
					.append(" inner join order_status_language osl on os.id = osl.order_status_id ")
					.append(" inner join language l on l.id = osl.language_id ")
					.append(" where l.locale =  ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			pst.setString(i++, locale);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				results.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		
		return results;
	}
	
	protected OrderStatus loadNext (ResultSet rs) throws SQLException{
		
		int i = 1;
		OrderStatus estado = new OrderStatus();
		
		estado.setId(rs.getInt(i++));
		estado.setName(rs.getString(i++));
		
		return estado;
	}

}
