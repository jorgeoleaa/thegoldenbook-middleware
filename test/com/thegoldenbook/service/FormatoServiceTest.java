package com.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Formato;
import com.thegoldenbook.service.FormatoService;
import com.thegoldenbook.service.impl.FormatoServiceImpl;

public class FormatoServiceTest {

	private static Logger logger = LogManager.getLogger(FormatoServiceTest.class);
	private FormatoService formatoService = null;

	public FormatoServiceTest() {
		formatoService = new FormatoServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing FindAll...");
		String locale = "it";
		List<Formato> formatos = formatoService.findAll(locale);
		if(formatos.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Formato f : formatos) {
				logger.info(f);
			}
		}
	}
	
	public static void main(String []args) throws Exception{
		FormatoServiceTest test = new FormatoServiceTest();
		test.testFindAll();
		
	}
}
