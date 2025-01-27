package com.pinguela.thegoldenbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.dao.LineaPedidoDAO;
import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.util.JDBCUtils;

public class LineaPedidoDAOImpl implements LineaPedidoDAO {
	
	private static Logger logger = LogManager.getLogger(LineaPedidoDAOImpl.class);
	
	public LineaPedidoDAOImpl() {
		
	}
	
	public List<LineaPedido> findByPedido(Connection con, Long pedidoId) throws DataException{
		
		List<LineaPedido> resultados = new ArrayList<LineaPedido>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" SELECT LP.ID, LP.PRECIO, LP.UNIDADES, LP.PEDIDO_ID, LP.LIBRO_ID, L.nombre ")
					.append(" FROM LINEA_PEDIDO LP ")
					.append(" INNER JOIN LIBRO L ON L.ID = LP.LIBRO_ID ")
					.append(" INNER JOIN PEDIDO p ON LP.PEDIDO_ID = p.ID  ")
					.append(" WHERE PEDIDO_ID = ? ")
					.append(" ORDER BY LP.ID DESC ");
				

			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pst.setLong(i++, pedidoId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				resultados.add(loadNext(rs));
			}
			
		} catch (SQLException e) {
			logger.error("PedidoID: "+pedidoId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return resultados;
	}

	public LineaPedido findById(Connection con, Long lineaPedidoId) throws DataException{
		
		LineaPedido lp = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
					
			
			StringBuilder query = new StringBuilder(" SELECT lp.ID, lp.PRECIO, lp.UNIDADES, lp.PEDIDO_ID, lp.LIBRO_ID, l.nombre ")
					.append(" FROM LINEA_PEDIDO LP ")
					.append(" INNER JOIN LIBRO L ON L.ID = LP.LIBRO_ID ")
					.append(" WHERE LP.ID = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pst.setLong(i++, lineaPedidoId);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				lp = loadNext(rs);
			}
			
			
		} catch (SQLException e) {
			logger.error("LineaPedidoID: "+lineaPedidoId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return lp;
	}
	
	public void create(Connection con, Long idPedido, List<LineaPedido> lineas) throws DataException{
		
		if (lineas.size()==0) return;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(
					 " INSERT INTO LINEA_PEDIDO(PRECIO, UNIDADES, PEDIDO_ID, LIBRO_ID) "
					+" VALUES ");
			
			query = JDBCUtils.appendMultipleInsertParameters(query, "(?,?,?,?)", lineas.size());
			
			ps = con.prepareStatement(
					query.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			for (LineaPedido linea: lineas) {
				ps.setDouble(i++, linea.getPrecio());
				ps.setInt(i++, linea.getUnidades());
				linea.setPedidoId(idPedido);
				ps.setLong(i++, idPedido);
				ps.setLong(i++, linea.getLibroId());				
			};
			
			int insertedRows = ps.executeUpdate();
			
			if(insertedRows!=lineas.size()) {
					
			} 
			
			rs = ps.getGeneratedKeys();
			
			i = 0;
			Long lineaPedidoId = null;
			
			while (rs.next()) {
				lineaPedidoId = rs.getLong(1);
				lineas.get(i).setId(lineaPedidoId);
				i++;
			} 
			
		} catch (SQLException e) {
			logger.error("PedidoID: "+idPedido, "Lista lineas: "+lineas, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(ps, rs);
		}
	}	

	public boolean deleteByPedido(Connection con, Long idPedido) throws DataException{		
		
		PreparedStatement pst = null;
		
		try {
					
			pst = con.prepareStatement(
					 " DELETE FROM LINEA_PEDIDO WHERE PEDIDO_ID = ?");
			
			int i = 1;
			pst.setLong(i++, idPedido);
			
			int deletedRows = pst.executeUpdate();
			
			if (deletedRows==0) {
				return false;
			} 
			
		} catch (SQLException e) {
			logger.error("PedidoID: "+idPedido, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}	
	
	
	public boolean deleteFromPedido(Connection c, Long lineaId, Long pedidoId) throws DataException{
		
		PreparedStatement pst = null;
		
		try {
			
			StringBuilder query = new StringBuilder("DELETE FROM LINEA_PEDIDO ")
					.append(" WHERE ID = ? AND PEDIDO_ID = ?");
			
			pst = c.prepareStatement(query.toString());
			
			int i = 1;
			pst.setLong(i++, lineaId);
			pst.setLong(i++, pedidoId);
			
			int deletedRows = pst.executeUpdate();
			
			if(deletedRows == 0) {
				return false;
			}
			
		}catch(SQLException e) {
			logger.error("PedidoID: "+pedidoId, " Linea id: "+lineaId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		
		
		return true;
	}
	
	protected LineaPedido loadNext (ResultSet rs) throws SQLException{
		
		int i = 1;
		
		LineaPedido l = new LineaPedido();
		
		l.setId(rs.getLong(i++));
		l.setPrecio(rs.getDouble(i++));
		l.setUnidades(rs.getInt(i++));
		l.setPedidoId(rs.getLong(i++));
		l.setLibroId(rs.getLong(i++));
		l.setNombreLibro(rs.getString(i++));
		
		return l;
	}

	
}
