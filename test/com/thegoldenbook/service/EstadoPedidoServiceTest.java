package com.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.OrderStatus;
import com.thegoldenbook.service.EstadoPedidoService;
import com.thegoldenbook.service.impl.EstadoPedidoServiceImpl;

public class EstadoPedidoServiceTest {

	private EstadoPedidoService estadoPedidoService = null;
	private static Logger logger = LogManager.getLogger(EstadoPedidoServiceImpl.class);
	
	public EstadoPedidoServiceTest() {
		estadoPedidoService = new EstadoPedidoServiceImpl();
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
