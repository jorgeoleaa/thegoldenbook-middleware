package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.OrderStatus;
import com.thegoldenbook.service.OrderStatusService;
import com.thegoldenbook.service.impl.OrderStatusServiceImpl;

public class EstadoPedidoServiceTest {

	private OrderStatusService estadoPedidoService = null;
	private static Logger logger = LogManager.getLogger(OrderStatusServiceImpl.class);
	
	public EstadoPedidoServiceTest() {
		estadoPedidoService = new OrderStatusServiceImpl();
	}
	
	public void testFindAll() {
		
		try {
			List<OrderStatus> estados = estadoPedidoService.findAll();
			logger.info(estados);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static void main (String []args) {
		EstadoPedidoServiceTest test = new EstadoPedidoServiceTest();
		test.testFindAll();
	}
}
