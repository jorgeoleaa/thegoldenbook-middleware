package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.LineaPedidoDAO;
import com.thegoldenbook.dao.impl.LineaPedidoDAOImpl;
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.service.LineaPedidoService;
import com.thegoldenbook.util.JDBCUtils;

public class LineaPedidoServiceImpl implements LineaPedidoService{

	private static Logger logger = LogManager.getLogger(LineaPedidoServiceImpl.class);
	private LineaPedidoDAO lineaPedidoDAO = null;

	public LineaPedidoServiceImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}

	public OrderItem findById(Long lineaPedidoId) throws DataException{

		Connection con = null;
		OrderItem lp = null;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			lp = lineaPedidoDAO.findById(con, lineaPedidoId);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return lp;
	}

	
	public boolean deleteFromPedido(Long lineaId, Long pedidoId) throws DataException {
		
		Connection con = null;
		boolean isDeleted = false;
		boolean commit = false;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			isDeleted = lineaPedidoDAO.deleteFromPedido(con, lineaId, pedidoId);
			commit = true;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		
		return isDeleted;
	}
}
