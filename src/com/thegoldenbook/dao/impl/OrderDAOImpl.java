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
import com.thegoldenbook.dao.OrderDAO;
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.OrderCriteria;
import com.thegoldenbook.util.JDBCUtils;

public class OrderDAOImpl implements OrderDAO{

	private static Logger logger = LogManager.getLogger(OrderDAOImpl.class);
	private OrderItemDAO orderItemDAO = null;

	public OrderDAOImpl() {		
		orderItemDAO = new OrderItemDAOImpl();
	}

	public Results<Order> findByCriteria (Connection con, OrderCriteria orderCriteria, int pos, int pageSize) throws DataException{

		Results<Order> results = new Results<Order>();
		List<String> conditions = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" select o.id, o.order_date, o.price, o.user_id, u.nickname, o.order_status_id, osl.name ")
					.append(" from `order` o ")
					.append(" inner join user u on u.id = o.user_id ")
					.append(" inner join order_status os on os.id = o.order_status_id ")
					.append(" inner join order_status_language osl on osl.order_status_id = o.order_status_id ")
					.append(" inner join language l on l.id = osl.language_id ");

			if (orderCriteria.getId() != null) {
				conditions.add(" o.id = ? ");
			}

			else {

				if(orderCriteria.getStartDate()!= null) {
					conditions.add(" o.order_date >= ? ");
				}

				if(orderCriteria.getEndDate() != null) {
					conditions.add(" o.order_date <= ? ");
				}

				if(orderCriteria.getMinPrice()!= null) {
					conditions.add(" o.price >= ? ");
				}

				if(orderCriteria.getMaxPrice()!= null) {
					conditions.add(" o.price <= ? ");
				}

				if(orderCriteria.getUserId()!= null) {
					conditions.add(" o.user_id = ? ");
				}

				if(orderCriteria.getOrderStatusId()!= null) {
					conditions.add(" o.order_status_id = ? ");
				}

			}

			if(!conditions.isEmpty()) {
				query.append(" where ");
				query.append(String.join(" and ", conditions));
			}
			
			query.append(" order by ").append(orderCriteria.getOrderBy()).append(orderCriteria.getAscDesc() ? " asc " : " desc ");
			
			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, orderCriteria.getLocale());

			if(orderCriteria.getId()!=null) {
				pst.setLong(i++, orderCriteria.getId());
				
			} else {
				if(orderCriteria.getStartDate()!=null) {
					pst.setDate(i++, new java.sql.Date(orderCriteria.getStartDate().getTime()));
				}
				if(orderCriteria.getEndDate()!=null) {
					pst.setDate(i++, new java.sql.Date(orderCriteria.getEndDate().getTime()));
				}
				if(orderCriteria.getMinPrice()!=null) {
					pst.setDouble(i++, orderCriteria.getMinPrice());
				}
				if(orderCriteria.getMaxPrice()!=null) {
					pst.setDouble(i++, orderCriteria.getMaxPrice());
				}
				if(orderCriteria.getUserId()!=null) {
					pst.setLong(i++, orderCriteria.getUserId());
				}
				if(orderCriteria.getOrderStatusId()!=null) {
					pst.setInt(i++, orderCriteria.getOrderStatusId());
				}
			}

			rs = pst.executeQuery();

			int count = 0;

			if((pos>=1) && rs.absolute(pos)) {

				do {
					results.getPage().add(loadNext(con, rs));
					count++;

				}while(count<pageSize && rs.next());
			}
			results.setTotal(JDBCUtils.getTotalRows(rs));

		}catch(SQLException e) {
			logger.error("OrderCriteria: "+orderCriteria, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return results;
	}




	public Order findBy(Connection con, Long id, String locale) throws DataException{

		Order p = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			StringBuilder query = new StringBuilder(" SELECT o.id, o.order_date, o.price, o.user_id, u.nickname, o.order_status_id, osl.name ")
					.append(" from `order` o ")
					.append(" inner join user u on u.id = o.user_id ")
					.append(" inner join order_status os on os.id = o.order_status_id ")
					.append(" inner join order_status_language osl on osl.order_status_id = o.order_status_id ")
					.append(" inner join language l on l.id = osl.language_id ")
					.append(" where l.locale = ? and o.id = ? ");

			pst = con.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			pst.setString(i++, locale);
			pst.setLong(i++, id);

			rs = pst.executeQuery();

			if(rs.next()) {
				p = loadNext(con, rs);
			}

		} catch (SQLException e) {
			logger.error("ID: "+id+" locale: "+locale, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst, rs);
		}
		return p;
	}


	public Long create(Connection con, Order order) throws DataException{

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {


			ps = con.prepareStatement(
					" insert into order(order_date, price, user_id, order_status_id) "
							+" values(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setDate(i++, new java.sql.Date(order.getOrderDate().getTime()));
			ps.setDouble(i++, order.getPrice());
			ps.setLong(i++, order.getUserId());
			ps.setInt(i++, order.getOrderStatusId());

			int insertedRows = ps.executeUpdate();

			if(insertedRows!=1) {
			} 

			rs = ps.getGeneratedKeys();

			if(rs.next()) {
				Long id = rs.getLong(1);
				order.setId(id);
				orderItemDAO.create(con, order.getId(), order.getOrderItems());
				return id;
			} 		

		} catch (SQLException e) {
			logger.error("Order: "+order, e);
			throw new DataException(e);
		} finally {
			JDBCUtils.close(ps, rs);
		}
		return order.getId();

	}

	public boolean update(Connection con, Order order) throws DataException{

		PreparedStatement pst = null;

		try {

			pst = con.prepareStatement(
					" update order set "
							+ " order_date = ?, "
							+ " price = ?, "
							+ " user_id = ?, "
							+ " order_status_id = ? "
							+ " where id = ?");			
			int i = 1;
			pst.setDate(i++, new java.sql.Date(order.getOrderDate().getTime()));
			pst.setDouble(i++, order.getPrice());
			pst.setLong(i++, order.getUserId());
			pst.setInt(i++, order.getOrderStatusId());
			pst.setLong(i++, order.getId());

			int updatedRows = pst.executeUpdate();

			if(updatedRows!=1) {
				return false;
			} 

			orderItemDAO.deleteByOrder(con, order.getId());
			orderItemDAO.create(con, order.getId(), order.getOrderItems());

		} catch (SQLException e) {
			logger.error("Order: "+order, e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(pst);
		}
		return true;
	}


	public boolean delete(Connection con, Long id) throws DataException{		

		PreparedStatement pst = null;
		
		try {

			orderItemDAO.deleteByOrder(con, id);

			pst = con.prepareStatement(
					" delete from order where id = ?");

			int i = 1;
			pst.setLong(i++, id);

			int deletedRows = pst.executeUpdate();

			if (deletedRows==0) {
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
		Order order = new Order();

		order.setId(rs.getLong(i++));
		order.setOrderDate(rs.getDate(i++));
		order.setPrice(rs.getDouble(i++));
		order.setUserId(rs.getLong(i++));
		order.setNickname(rs.getString(i++));
		order.setOrderStatusId(rs.getInt(i++));
		order.setOrderStatusName(rs.getString(i++));

		List<OrderItem> orderItems = orderItemDAO.findByOrder(con, order.getId());
		order.setOrderItems(orderItems);

		return order;
	}
}
