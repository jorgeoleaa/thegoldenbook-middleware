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

public class EstadoPedidoDAOImpl implements OrderStatusDAO{

	private static Logger logger = LogManager.getLogger(EstadoPedidoDAOImpl.class);
	
	public List<OrderStatus> findAll(Connection con) throws DataException {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<OrderStatus> estados = new ArrayList<OrderStatus>();
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT ID, NOMBRE ").append(" FROM TIPO_ESTADO_PEDIDO ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				estados.add(loadNext(rs));
			}
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		
		return estados;
	}
	
	protected OrderStatus loadNext (ResultSet rs) throws SQLException{
		
		int i = 1;
		OrderStatus estado = new OrderStatus();
		
		estado.setId(rs.getInt(i++));
		estado.setNombre(rs.getString(i++));
		
		return estado;
	}

}
