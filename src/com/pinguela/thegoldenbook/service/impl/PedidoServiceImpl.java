package com.pinguela.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.PedidoDAO;
import com.pinguela.thegoldenbook.dao.impl.PedidoDAOImpl;
import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.MailException;
import com.pinguela.thegoldenbook.service.MailService;
import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class PedidoServiceImpl implements PedidoService {

	private static Logger logger = LogManager.getLogger(PedidoServiceImpl.class);
	private PedidoDAO pedidoDAO = null;
	private ClienteService clienteService = null;
	private MailService mailService = null;

	public PedidoServiceImpl() {
		pedidoDAO = new PedidoDAOImpl();
		clienteService = new ClienteServiceImpl();
		mailService = new MailServiceImpl();
	}


	public Pedido findBy(Long id) throws DataException{

		Connection con = null;
		Pedido p = null;
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



	public Results<Pedido> findByCriteria(PedidoCriteria pedido, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Pedido> resultados = null;
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



	public Long create(Pedido p) throws DataException, MailException {

	    Connection con = null;
	    Long id = null;
	    boolean commit = false;

	    try {
	        con = JDBCUtils.getConnection();
	        con.setAutoCommit(false);

	        PedidoCriteria criteria = new PedidoCriteria();
	        criteria.setTipoEstadoPedidoId(7);  // Tipo de estado "carrito"
	        criteria.setClienteId(p.getClienteId());

	        List<Pedido> pedidos = findByCriteria(criteria, 1, Integer.MAX_VALUE).getPage();

	        Pedido carrito = null;
	        if (!pedidos.isEmpty()) {
	            carrito = pedidos.get(0);
	        }

	        if (carrito == null || p.getTipoEstadoPedidoId() != 7) {
	            p.setPrecio(calcularPrecio(p));
	            id = pedidoDAO.create(con, p);
	            if (id != null) {
	            	ClienteDTO cliente = clienteService.findById(id);
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




	public boolean update(Pedido p) throws DataException{

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




	public Double calcularPrecio(Pedido p) throws DataException{

		double precioTotal = 0.0d;

		for(LineaPedido lp:p.getLineas()) {
			precioTotal+= lp.getPrecio()*lp.getUnidades();
		}

		return precioTotal;

	}


}
