package com.pinguela.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;

public class GeneroLiterarioServiceTest {
	
	private Logger logger = LogManager.getLogger(GeneroLiterarioServiceTest.class);
	private GeneroLiterarioService generoLiterarioService = null;
	
	public GeneroLiterarioServiceTest() {
		generoLiterarioService = new GeneroLiterarioServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		String locale = "it";
		List<GeneroLiterario> generos = generoLiterarioService.findAll(locale);
		
		if(generos.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(GeneroLiterario gl : generos) {
				logger.info(gl);
			}
		}
		
	}
	
	public static void main(String [] args) throws Exception{
		GeneroLiterarioServiceTest test = new GeneroLiterarioServiceTest();
		test.testFindAll();
	}
}
