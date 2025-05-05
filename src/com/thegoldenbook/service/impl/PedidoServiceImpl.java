package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.OrderDAO;
import com.thegoldenbook.dao.impl.PedidoDAOImpl;
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.ClienteService;
import com.thegoldenbook.service.MailException;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.OrderCriteria;
import com.thegoldenbook.service.PedidoService;
import com.thegoldenbook.util.JDBCUtils;

public class PedidoServiceImpl implements PedidoService {

	private static Logger logger = LogManager.getLogger(PedidoServiceImpl.class);
	private OrderDAO pedidoDAO = null;
	private ClienteService clienteService = null;
	private MailService mailService = null;

	public PedidoServiceImpl() {
		pedidoDAO = new PedidoDAOImpl();
		clienteService = new ClienteServiceImpl();
		mailService = new MailServiceImpl();
	}


	public Order findBy(Long id) throws DataException{

		Connection con = null;
		Order p = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			p = pedidoDAO.findBy(con, id);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return p;
	}



	public Results<Order> findByCriteria(OrderCriteria pedido, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Order> resultados = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			resultados = pedidoDAO.findByCriteria(con, pedido, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return resultados;
	}



	public Long create(Order p) throws DataException, MailException {

	    Connection con = null;
	    Long id = null;
	    boolean commit = false;

	    try {
	        con = JDBCUtils.getConnection();
	        con.setAutoCommit(false);

	        OrderCriteria criteria = new OrderCriteria();
	        criteria.setTipoEstadoPedidoId(7);  // Tipo de estado "carrito"
	        criteria.setClienteId(p.getClienteId());

	        List<Order> pedidos = findByCriteria(criteria, 1, Integer.MAX_VALUE).getPage();

	        Order carrito = null;
	        if (!pedidos.isEmpty()) {
	            carrito = pedidos.get(0);
	        }

	        if (carrito == null || p.getTipoEstadoPedidoId() != 7) {
	            p.setPrecio(calcularPrecio(p));
	            id = pedidoDAO.create(con, p);
	            if (id != null) {
	            	User cliente = clienteService.findById(id);
	            	mailService.sendPedidoRealizado(cliente.getEmail(), cliente, p);
	                commit = true;
	            }
	        }

	    } catch (SQLException e) {
	        logger.error(e.getMessage(), e);
	        throw new DataException(e);
	    } finally {
	        JDBCUtils.close(con, commit);
	    }
	    return id;
	}




	public boolean update(Order p) throws DataException{

		Connection con = null;
		boolean tf = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			p.setPrecio(calcularPrecio(p));
			tf = pedidoDAO.update(con, p);
			commit = true;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return tf;
	}



	public boolean delete(Long id) throws DataException{

		Connection con = null;
		boolean tf = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			tf = pedidoDAO.delete(con, id);
			commit = true;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return tf;
	}




	public Double calcularPrecio(Order p) throws DataException{

		double precioTotal = 0.0d;

		for(OrderItem lp:p.getLineas()) {
			precioTotal+= lp.getPrecio()*lp.getUnidades();
		}

		return precioTotal;

	}


}
