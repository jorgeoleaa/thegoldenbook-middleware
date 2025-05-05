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
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.util.JDBCUtils;

public class OrderItemDAOImpl implements OrderItemDAO {
	
	private static Logger logger = LogManager.getLogger(OrderItemDAOImpl.class);
	
	public OrderItemDAOImpl() {
		
	}
	
	public List<OrderItem> findByOrder(Connection con, Long orderId) throws DataException{
		
		List<OrderItem> results = new ArrayList<OrderItem>();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(" select oi.id, oi.price, oi.quantity, oi.order_id, oi.book_id, b.title ")
					.append(" from order_item oi ")
					.append(" inner join book b ON b.id = oi.book_id")
					.append(" inner join `order` o ON oi.order_id = o.id ")
					.append(" where oi.order_id = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pst.setLong(i++, orderId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				results.add(loadNext(rs));
			}
			
		} catch (SQLException e) {
			logger.error("OrderId: "+orderId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		
		return results;
	}

	public OrderItem findById(Connection con, Long orderItemId) throws DataException{
		
		OrderItem lp = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
					
			
			StringBuilder query = new StringBuilder(" select oi.id, oi.price, oi.quantity, oi.order_id, oi.book_id, b.title ")
					.append(" from order_item oi ")
					.append(" inner join book b ON b.id = oi.book_id")
					.append(" WHERE oi.id = ? ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			pst.setLong(i++, orderItemId);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				lp = loadNext(rs);
			}
			
			
		} catch (SQLException e) {
			logger.error("OrderItemId: "+orderItemId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return lp;
	}
	
	public void create(Connection con, Long orderId, List<OrderItem> items) throws DataException{
		
		if (items.size()==0) return;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			StringBuilder query = new StringBuilder(
					 " insert into order_item(price, quantity, order_id, book_id) "
					+" values ");
			
			query = JDBCUtils.appendMultipleInsertParameters(query, "(?,?,?,?)", items.size());
			
			ps = con.prepareStatement(
					query.toString(),
					Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			for (OrderItem item: items) {
				ps.setDouble(i++, item.getPrice());
				ps.setInt(i++, item.getQuantity());
				item.setOrderId(orderId);
				ps.setLong(i++, orderId);
				ps.setLong(i++, item.getBookId());				
			};
			
			int insertedRows = ps.executeUpdate();
			
			if(insertedRows!=items.size()) {
					
			} 
			
			rs = ps.getGeneratedKeys();
			
			i = 0;
			Long orderItemId = null;
			
			while (rs.next()) {
				orderItemId = rs.getLong(1);
				items.get(i).setId(orderItemId);
				i++;
			} 
			
		} catch (SQLException e) {
			logger.error("Order id: "+orderId, "Items list: "+items, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(ps, rs);
		}
	}	

	public boolean deleteByOrder(Connection con, Long orderId) throws DataException{		
		
		PreparedStatement pst = null;
		
		try {
					
			pst = con.prepareStatement(
					 " delete from order_item where order_id = ?");
			
			int i = 1;
			pst.setLong(i++, orderId);
			
			int deletedRows = pst.executeUpdate();
			
			if (deletedRows==0) {
				return false;
			} 
			
		} catch (SQLException e) {
			logger.error("OrderId: "+orderId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}	
	
	
	public boolean deleteFromOrder(Connection c, Long orderItemId, Long orderId) throws DataException{
		
		PreparedStatement pst = null;
		
		try {
			
			StringBuilder query = new StringBuilder("delete from order_item ")
					.append(" where id = ? and order_id = ?");
			
			pst = c.prepareStatement(query.toString());
			
			int i = 1;
			pst.setLong(i++, orderItemId);
			pst.setLong(i++, orderId);
			
			int deletedRows = pst.executeUpdate();
			
			if(deletedRows == 0) {
				return false;
			}
			
		}catch(SQLException e) {
			logger.error("OrderId: "+orderId, " OrderItemId: "+orderItemId, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		
		
		return true;
	}
	
	protected OrderItem loadNext (ResultSet rs) throws SQLException{
		
		int i = 1;
		
		OrderItem l = new OrderItem();
		
		l.setId(rs.getLong(i++));
		l.setPrice(rs.getDouble(i++));
		l.setQuantity(rs.getInt(i++));
		l.setOrderId(rs.getLong(i++));
		l.setBookId(rs.getLong(i++));
		l.setBookTitle(rs.getString(i++));
		
		return l;
	}

	
}
