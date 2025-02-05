package com.pinguela.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.service.impl.IdiomaServiceImpl;

public class IdiomaServiceTest {

	private static Logger logger = LogManager.getLogger(IdiomaServiceTest.class);
	private IdiomaService idiomaService = null;

	public IdiomaServiceTest() {
		idiomaService = new IdiomaServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll..");
		String locale = "it";
		List<Idioma> idiomas = idiomaService.findAll(locale);

		if(idiomas.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Idioma i : idiomas) {
				logger.info(i);
			}
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findById...");
		String locale = "it";
		Idioma idioma = idiomaService.findById(locale, 2);
		if(idioma != null) {
			logger.info(idioma);
		}else {
			logger.info("No se han encontrado resultados");
		}
	}

	public static void main (String [] args) throws Exception{
		IdiomaServiceTest test = new IdiomaServiceTest();
		test.testFindAll();
		//test.testFindById();
	}
}
