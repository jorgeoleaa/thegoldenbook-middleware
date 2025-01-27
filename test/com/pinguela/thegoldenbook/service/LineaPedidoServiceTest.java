package com.pinguela.thegoldenbook.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {

	private static Logger logger = LogManager.getLogger(LineaPedidoServiceTest.class);
	private LineaPedidoService lineaPedidoService = null;
	
	public LineaPedidoServiceTest() {
		lineaPedidoService = new LineaPedidoServiceImpl();
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		LineaPedido lp = lineaPedidoService.findById(3l);
		
		if(lp == null) {
			logger.info("No se han encontrado resultados con el ID proporcionado");
		}else {
			logger.info(lp);
		}
	}
	
	public static void main (String [] args) throws Exception{
		LineaPedidoServiceTest test = new LineaPedidoServiceTest();
		test.testFindById();
	}
}
