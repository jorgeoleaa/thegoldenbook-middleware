package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.EstadoPedidoDAO;
import com.pinguela.thegoldenbook.dao.impl.EstadoPedidoDAOImpl;
import com.pinguela.thegoldenbook.model.EstadoPedido;
import com.pinguela.thegoldenbook.service.EstadoPedidoService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class EstadoPedidoServiceImpl implements EstadoPedidoService{

	private static Logger logger = LogManager.getLogger();
	private EstadoPedidoDAO estadoPedidoDAO = null;
	
	public EstadoPedidoServiceImpl() {
		estadoPedidoDAO = new EstadoPedidoDAOImpl();
	}
	
	@Override
	public List<EstadoPedido> findAll() throws DataException {
		
		Connection con = null;
		List<EstadoPedido> estados = null;
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
