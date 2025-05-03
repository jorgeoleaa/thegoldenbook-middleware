package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.OrderStatusDAO;
import com.thegoldenbook.dao.impl.OrderStatusDAOImpl;
import com.thegoldenbook.model.OrderStatus;
import com.thegoldenbook.service.OrderStatusService;
import com.thegoldenbook.util.JDBCUtils;

public class EstadoPedidoServiceImpl implements OrderStatusService{

	private static Logger logger = LogManager.getLogger();
	private OrderStatusDAO estadoPedidoDAO = null;
	
	public EstadoPedidoServiceImpl() {
		estadoPedidoDAO = new OrderStatusDAOImpl();
	}
	
	@Override
	public List<OrderStatus> findAll() throws DataException {
		
		Connection con = null;
		List<OrderStatus> estados = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			estados = estadoPedidoDAO.findAll(con);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return estados;
	}

}
