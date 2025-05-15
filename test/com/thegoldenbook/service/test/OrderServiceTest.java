package com.thegoldenbook.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.model.Order;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.BookService;
import com.thegoldenbook.service.OrderCriteria;
import com.thegoldenbook.service.OrderService;
import com.thegoldenbook.service.impl.BookServiceImpl;
import com.thegoldenbook.service.impl.OrderServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class OrderServiceTest {

	private static Logger logger = LogManager.getLogger(OrderServiceTest.class);
	private OrderService orderService = null;
	private BookService bookService = null;

	public OrderServiceTest() {
		orderService = new OrderServiceImpl();
		bookService = new BookServiceImpl();
	}

	public void testFindById() throws Exception{
		logger.info("Testing FindById...");
		Order p = null;
		orderService = new OrderServiceImpl();
		p = orderService.findBy(3l);
		if(p == null){
			logger.info("No se han encontrado resultados a partir del identificador introducido");
		}else {
			logger.info(p);
		}
	}

	public void testFindByCriteriaId() throws Exception{
		logger.info("Testing FindByCriteriaId...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setId(2l);
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado resultados a partir del identificador introducido");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}

	public void testFindByCriteriaFechaDesde() throws Exception{
		logger.info("Testing FindByCriteriaFechaDesde...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setFechaDesde(DateUtils.getDate(2023, 9, 1));
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos realizados a partir de la fecha proporcionada");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}

	public void testFindByCriteriaFechaHasta() throws Exception{
		logger.info("Testing FindByCriteriaFechaHasta...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setFechaHasta(DateUtils.getDate(2023, 4, 1));
		criteria.setAscDesc(Boolean.TRUE);
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);
		
		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos realizados antes de la fecha proporcionada");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaPrecioDesde() throws Exception{
		logger.info("Testing FindByCriteriaPrecioDesde...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setPrecioDesde(40.00);
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos con un precio superior al proporcionado");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaPrecioHasta() throws Exception{
		logger.info("Testing FindByCriteriaPrecioHasta...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setPrecioHasta(20.00);
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos con un precio inferior al proporcionado");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaClienteId() throws Exception{
		logger.info("Testing FindByCriteriaClienteId...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setClienteId(2l);
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos realizados por el cliente proporcionado");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaTipoEstadoPedidoId() throws Exception{
		logger.info("Testing FindByCriteriaTipoEstadoPedidoId...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setTipoEstadoPedidoId(2);
		Results<Order>resultados = orderService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos a partir del del estado proporcionado");
		}else {
			for(Order p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaMultipleParameters() throws Exception{
		logger.info("Testing findByCriteriaMultipleParameters...");
		OrderCriteria criteria = new OrderCriteria();
		criteria.setMinPrice(16.0d);
		criteria.setMaxPrice(20.00);
		criteria.setLocale("es_ES");
		Results<Order> results = orderService.findByCriteria(criteria, 1, 7);
		
		if (results.getPage().isEmpty()) {
			logger.info("There are no results");
		}else {
			for (Order o : results.getPage()) {
				logger.info(o);
			}
		}
	
	}
	
	public void testFindByEmptyCriteria() throws Exception{
		logger.info("Testing findByEmptyCriteria...");
		OrderCriteria criteria = new OrderCriteria();
		Results<Order> resultados = orderService.findByCriteria(criteria, 1, 7);
		if (resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado resultados a partir de los parámetros introducidos");
		}else {
			for (Order p : resultados.getPage()) {
				logger.info(p);
			}
		}
	
	}
	
	public void testCreate() throws Exception{
		
		String locale = "es_ES";
		
		Order p = new Order ();
		OrderItem lp1 = new OrderItem();
		OrderItem lp2 = new OrderItem();
		OrderItem lp3 = new OrderItem();
		Book libro = bookService.findByBook(locale, 6l);

		p.setOrderDate(new Date());
		p.setUserId(10l);
		p.setOrderStatusId(2);
		
		lp3.setBookId(libro.getId());
		lp3.setPrice(libro.getPrice());
		lp3.setQuantity(1);
		
		p.getOrderItems().add(lp3);
		
		p.setPrice(orderService.calculatePrice(p));
		
		
//		p.getLineas().add(lp1);
		//p.getLineas().add(lp2);
		

		Long id = orderService.create(p, locale);
		
		if(id == null) {
			logger.info("The order was not created correctly");
		}else {
			logger.info("The order was created correctly");
		}
	}

	public void testUpdate() throws Exception{
		logger.info("Testing update...");
		Order order = orderService.findBy(21l, "es_ES");
		order.setOrderStatusId(3);
		order.setUserId(4l);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		OrderItem lp = new OrderItem();
		lp.setBookId(3l);
		lp.setOrderId(21l);
		lp.setPrice(10.00);
		lp.setQuantity(1);
		orderItems.add(lp);
		order.setOrderItems(orderItems);
		boolean b =	orderService.update(order);
	
		if(b == false) {
			logger.info("The order was not updated");
		}else {
			logger.info("The order was updated");
		}
	}
	
	public void testDelete() throws Exception{
		logger.info("Testing delete...");
		Order p = new Order();
		p.setId(12l);
		orderService.delete(p.getId());
		
		if(orderService.findBy(p.getId()) == null) {
			logger.info("Pedido eliminado correctamente");
		}else {
			logger.info("No se ha borrado el pedido con ID proporcionado porque no se encuentra en la BD");
		}
		
	}

	public static void main (String[]args) throws Exception{
		OrderServiceTest test = new OrderServiceTest();
		//test.testFindById();
		//test.testFindByCriteriaId();
		//test.testFindByCriteriaFechaDesde();
		//test.testFindByCriteriaFechaHasta();
		//test.testFindByCriteriaPrecioDesde();
		//test.testFindByCriteriaPrecioHasta();
		//test.testFindByCriteriaClienteId();
		//test.testFindByCriteriaTipoEstadoPedidoId();
		//test.testFindByCriteriaMultipleParameters();
		//test.testFindByEmptyCriteria();
		//test.testCreate();
		test.testUpdate();
		//test.testDelete();
		
	}

}
