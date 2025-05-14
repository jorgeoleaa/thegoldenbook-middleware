package com.thegoldenbook.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.dao.OrderDAO;
import com.thegoldenbook.dao.impl.OrderDAOImpl;
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.UserService;
import com.thegoldenbook.service.MailException;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.OrderCriteria;
import com.thegoldenbook.service.OrderService;
import com.thegoldenbook.util.JDBCUtils;

public class OrderServiceImpl implements OrderService {

	private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
	private OrderDAO orderDAO = null;
	private UserService userService = null;
	private MailService mailService = null;

	public OrderServiceImpl() {
		orderDAO = new OrderDAOImpl();
		userService = new UserServiceImpl();
		mailService = new MailServiceImpl();
	}


	public Order findBy(Long id, String locale) throws DataException{

		Connection con = null;
		Order order = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			order = orderDAO.findBy(con, id, locale);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return order;
	}



	public Results<Order> findByCriteria(OrderCriteria order, int pos, int pageSize) throws DataException{

		Connection con = null;
		Results<Order> results = null;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			results = orderDAO.findByCriteria(con, order, pos, pageSize);
			commit = true;

		} catch(SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return results;
	}



	public Long create(Order order, String locale) throws DataException, MailException {

	    Connection con = null;
	    Long id = null;
	    boolean commit = false;

	    try {
	        con = JDBCUtils.getConnection();
	        con.setAutoCommit(false);

	        OrderCriteria criteria = new OrderCriteria();
	        criteria.setOrderStatusId(6);  //Order status "cart"
	        criteria.setUserId(order.getUserId());

	        List<Order> orders = findByCriteria(criteria, 1, Integer.MAX_VALUE).getPage();

	        Order cart = null;
	        if (!orders.isEmpty()) {
	            cart = orders.get(0);
	        }

	        if (cart == null || order.getOrderStatusId() != 6) {
	            order.setPrice(calculatePrice(order));
	            id = orderDAO.create(con, order);
	            if (id != null) {
	            	User cliente = userService.findById(order.getUserId(), locale);
	            	mailService.notifyOrderPlaced(cliente.getEmail(), cliente, order);
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




	public boolean update(Order order) throws DataException{

		Connection con = null;
		boolean tf = false;
		boolean commit = false;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			order.setPrice(calculatePrice(order));
			tf = orderDAO.update(con, order);
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
			tf = orderDAO.delete(con, id);
			commit = true;

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new DataException(e);
		}finally {
			JDBCUtils.close(con, commit);
		}
		return tf;
	}




	public Double calculatePrice(Order order) throws DataException{

		double totalPrice = 0.0d;

		for(OrderItem lp:order.getOrderItems()) {
			totalPrice+= lp.getPrice()*lp.getQuantity();
		}

		return totalPrice;

	}


}
