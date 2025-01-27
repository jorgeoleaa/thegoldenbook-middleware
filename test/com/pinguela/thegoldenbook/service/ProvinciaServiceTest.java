package com.pinguela.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	
	private static Logger logger = LogManager.getLogger(ProvinciaServiceTest.class);
	private ProvinciaService provinciaService = null;
	
	public ProvinciaServiceTest() {
		provinciaService = new ProvinciaServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		List<Provincia> resultados = provinciaService.findAll();
		if(resultados.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Provincia p : resultados) {
				logger.info(p);
			}	
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findByProvinciaId...");
		Provincia p = provinciaService.findById(4);
		
		if(p!=null) {
			logger.info(p);
		}else {
			logger.trace("No se han encontrado resultados");
		}
	}
	
	public static void main(String[]args) throws Exception{
		ProvinciaServiceTest test = new ProvinciaServiceTest();
		test.testFindAll();
		test.testFindById();
	}

}
