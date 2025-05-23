package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Format;
import com.thegoldenbook.service.FormatService;
import com.thegoldenbook.service.impl.FormatServiceImpl;

public class FormatoServiceTest {

	private static Logger logger = LogManager.getLogger(FormatoServiceTest.class);
	private FormatService formatoService = null;

	public FormatoServiceTest() {
		formatoService = new FormatServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing FindAll...");
		String locale = "it";
		List<Format> formatos = formatoService.findAll(locale);
		if(formatos.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Format f : formatos) {
				logger.info(f);
			}
		}
	}
	
	public static void main(String []args) throws Exception{
		FormatoServiceTest test = new FormatoServiceTest();
		test.testFindAll();
		
	}
}
