package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.EmployeeType;
import com.thegoldenbook.service.EmployeeTypeService;
import com.thegoldenbook.service.impl.EmployeeTypeServiceImpl;

public class TipoEmpleadoServiceTest {
	
	private EmployeeTypeService tipoEmpleadoService = new EmployeeTypeServiceImpl();
	
	private static Logger logger = LogManager.getLogger(TipoEmpleadoServiceTest.class);
	
	public void testFindAll() throws Exception {
		
		List<EmployeeType> tipos = tipoEmpleadoService.findAll();
		logger.info(tipos);
	}
	
	public static void main (String [] args) throws Exception{
		TipoEmpleadoServiceTest test = new TipoEmpleadoServiceTest();
		test.testFindAll();
	}
}
