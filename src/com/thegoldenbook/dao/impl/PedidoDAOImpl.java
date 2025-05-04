package com.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.OrderItemDAO;
import com.thegoldenbook.dao.PedidoDAO;
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.PedidoCriteria;
import com.thegoldenbook.util.JDBCUtils;

public class PedidoDAOImpl implements PedidoDAO{

	private static Logger logger = LogManager.getLogger(PedidoDAOImpl.class);
	private OrderItemDAO lineaPedidoDAO = null;

	public PedidoDAOImpl() {		
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}

	public Results<Order> findByCriteria (Connection con, PedidoCriteria p, int pos, int pageSize) throws DataException{

		Results<Order> resultados = new Results<Order>();
		List<String> condiciones = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT P.ID, P.FECHA_REALIZACION, P.PRECIO, P.CLIENTE_ID, C.NICKNAME, P.TIPO_ESTADO_PEDIDO_ID, TP.NOMBRE ")
					.append(" FROM PEDIDO P ")
					.append(" INNER JOIN CLIENTE C ON C.ID = P.CLIENTE_ID")
					.append(" INNER JOIN TIPO_ESTADO_PEDIDO TP ON TP.ID = P.TIPO_ESTADO_PEDIDO_ID");

			if (p.getId() != null) {
				condiciones.add(" P.ID = ? ");
			}

			else {

				if(p.getFechaDesde()!= null) {
					condiciones.add(" P.FECHA_REALIZACION >= ? ");
				}

				if(p.getFechaHasta() != null) {
					condiciones.add(" P.FECHA_REALIZACION <= ? ");
				}

				if(p.getPrecioDesde()!= null) {
					condiciones.add(" P.PRECIO >= ? ");
				}

				if(p.getPrecioHasta()!= null) {
					condiciones.add(" P.PRECIO <= ? ");
				}

				if(p.getClienteId()!= null) {
					condiciones.add(" P.CLIENTE_ID = ? ");
				}

				if(p.getTipoEstadoPedidoId()!= null) {
					condiciones.add(" P.TIPO_ESTADO_PEDIDO_ID = ? ");
				}

			}

			if(!condiciones.isEmpty()) {
				query.append(" WHERE ");
				query.append(String.join(" AND ", condiciones));
			}
			
			query.append(" ORDER BY ").append(p.getOrderBy()).append(p.getAscDesc() ? " ASC " : " DESC ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;

			if(p.getId()!=null) {
				pst.setLong(i++, p.getId());
				
			} else {
				if(p.getFechaDesde()!=null) {
					pst.setDate(i++, new java.sql.Date(p.getFechaDesde().getTime()));
				}
				if(p.getFechaHasta()!=null) {
					pst.setDate(i++, new java.sql.Date(p.getFechaHasta().getTime()));
				}
				if(p.getPrecioDesde()!=null) {
					pst.setDouble(i++, p.getPrecioDesde());
				}
				if(p.getPrecioHasta()!=null) {
					pst.setDouble(i++, p.getPrecioHasta());
				}
				if(p.getClienteId()!=null) {
					pst.setLong(i++, p.getClienteId());
				}
				if(p.getTipoEstadoPedidoId()!=null) {
					pst.setInt(i++, p.getTipoEstadoPedidoId());
				}
			}

			rs = pst.executeQuery();

			int count = 0;

			if((pos>=1) && rs.absolute(pos)) {

				do {
					resultados.getPage().add(loadNext(con, rs));
					count++;

				}while(count<pageSize && rs.next());
			}
			resultados.setTotal(JDBCUtils.getTotalRows(rs));

		}catch(SQLException e) {
			logger.error("PedidoCriteria: "+p, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return resultados;
	}




	public Order findBy(Connection con, Long id) throws DataException{

		Order p = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT P.ID, P.FECHA_REALIZACION, P.PRECIO, P.CLIENTE_ID, C.NICKNAME, P.TIPO_ESTADO_PEDIDO_ID, TP.NOMBRE ")
					.append(" FROM PEDIDO P ")
					.append(" INNER JOIN CLIENTE C ON C.ID = P.CLIENTE_ID ")
					.append(" INNER JOIN TIPO_ESTADO_PEDIDO TP ON TP.ID = P.TIPO_ESTADO_PEDIDO_ID ")
					.append(" WHERE P.ID = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setLong(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				p = loadNext(con, rs);
			}

		} catch (SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return p;
	}


	public Long create(Connection con, Order p) throws DataException{

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			ps = con.prepareStatement(
					" INSERT INTO PEDIDO(FECHA_REALIZACION, PRECIO, CLIENTE_ID, TIPO_ESTADO_PEDIDO_ID) "
							+" VALUES(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setDate(i++, new java.sql.Date(p.getFechaRealizacion().getTime()));
			ps.setDouble(i++, p.getPrecio());
			ps.setLong(i++, p.getClienteId());
			ps.setInt(i++, p.getTipoEstadoPedidoId());

			int insertedRows = ps.executeUpdate();

			if(insertedRows!=1) {
				// throw new Excepction
			} 

			rs = ps.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				p.setId(id);
				lineaPedidoDAO.create(con, p.getId(), p.getLineas());
				return id;
			} 		

		} catch (SQLException e) {
			logger.error("Pedido: "+p, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(ps, rs);
		}
		return p.getId();

	}

	public boolean update(Connection con, Order p) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(
					" UPDATE PEDIDO SET "
							+ " FECHA_REALIZACION = ?, "
							+ " PRECIO = ?, "
							+ " CLIENTE_ID = ?, "
							+ " TIPO_ESTADO_PEDIDO_ID = ? "
							+ " WHERE ID = ?");			
			int i = 1;
			pst.setDate(i++, new java.sql.Date(p.getFechaRealizacion().getTime()));
			pst.setDouble(i++, p.getPrecio());
			pst.setLong(i++, p.getClienteId());
			pst.setInt(i++, p.getTipoEstadoPedidoId());
			pst.setLong(i++, p.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows!=1) {
				// Normalmente será porque lo ha borrado
				// otro proceso
				return false;
			} 

			lineaPedidoDAO.deleteByPedido(con, p.getId());
			lineaPedidoDAO.create(con, p.getId(), p.getLineas());

		} catch (SQLException e) {
			logger.error("Pedido: "+p, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public boolean delete(Connection con, Long id) throws DataException{		

		PreparedStatement pst = null;
		
		try {

			lineaPedidoDAO.deleteByPedido(con, id);

			pst = con.prepareStatement(
					" DELETE FROM PEDIDO WHERE ID = ?");

			int i = 1;
			pst.setLong(i++, id);

			int deletedRows = pst.executeUpdate();

			if (deletedRows==0) {
				// No pasa nada realmente, seguramente
				// ha sido ya ha sido borrado en otro proceso
				return false;
			} 

		} catch (SQLException e) {
			logger.error("ID: "+id, e);
			throw new DataException(e);
		}finally{
			JDBCUtils.close(pst);
		}
		return true;
	}
	protected Order loadNext (Connection con, ResultSet rs) throws SQLException, DataException{

		int i = 1;
		Order p = new Order();

		p.setId(rs.getLong(i++));
		p.setFechaRealizacion(rs.getDate(i++));
		p.setPrecio(rs.getDouble(i++));
		p.setClienteId(rs.getLong(i++));
		p.setNickname(rs.getString(i++));
		p.setTipoEstadoPedidoId(rs.getInt(i++));
		p.setTipoEstadoPedidoNombre(rs.getString(i++));

		List<OrderItem> lineas = lineaPedidoDAO.findByPedido(con, p.getId());
		p.setLineas(lineas);

		return p;
	}
}
