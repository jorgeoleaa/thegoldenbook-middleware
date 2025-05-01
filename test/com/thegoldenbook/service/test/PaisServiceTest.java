package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Country;
import com.thegoldenbook.service.PaisService;
import com.thegoldenbook.service.impl.PaisServiceImpl;

public class PaisServiceTest {
	
	private static Logger logger = LogManager.getLogger(PaisServiceTest.class);
	private PaisService paisService = null;

	public PaisServiceTest() {
		paisService = new PaisServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		List<Country> paises = paisService.findAll();
		if(paises.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Country p : paises) {
				logger.info(p);
			}
		}
	}
	
	public void testFindById() throws Exception {
		logger.traceEntry("Testing findByID...");
		Country p = paisService.findById(-1);
		
		if(p == null) {
			logger.trace("No se han encontrado resultados");
		}else {
			logger.info(p);
		}
	}
	
	public static void main (String[]args) throws Exception{
		PaisServiceTest test = new PaisServiceTest();
		//test.testFindAll();
		test.testFindById();
	}
}
