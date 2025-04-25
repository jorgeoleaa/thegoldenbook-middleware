package com.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.TipoEmpleado;
import com.thegoldenbook.service.TipoEmpleadoService;
import com.thegoldenbook.service.impl.TipoEmpleadoServiceImpl;

public class TipoEmpleadoServiceTest {
	
	private TipoEmpleadoService tipoEmpleadoService = new TipoEmpleadoServiceImpl();
	
	private static Logger logger = LogManager.getLogger(TipoEmpleadoServiceTest.class);
	
	public void testFindAll() throws Exception {
		
		List<TipoEmpleado> tipos = tipoEmpleadoService.findAll();
		logger.info(tipos);
	}
	
	public static void main (String [] args) throws Exception{
		TipoEmpleadoServiceTest test = new TipoEmpleadoServiceTest();
		test.testFindAll();
	}
}
