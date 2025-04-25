package com.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Tematica;
import com.thegoldenbook.service.TematicaService;
import com.thegoldenbook.service.impl.TematicaServiceImpl;

public class TematicaServiceTest {

	private static Logger logger = LogManager.getLogger(TematicaServiceTest.class);
	private TematicaService tematicaService = null;

	public TematicaServiceTest() {
		tematicaService = new TematicaServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing FindAll...");
		String locale = "it";
		List<Tematica> tematicas = tematicaService.findAll(locale);

		if(tematicas.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {

			for(Tematica t : tematicas) {
				logger.info(t);
			}
		}
	}
	
	
	public void testFindByLibroId() throws Exception{
		logger.traceEntry("Testing findByLibroId...");
		String locale = "it";
		List<Tematica> tematicas = tematicaService.findByLibro(locale, 3l);
		
		if(tematicas.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Tematica t : tematicas) {
				logger.info(t);
			}
		}
	}

	public static void main (String [] args) throws Exception{
		TematicaServiceTest test = new TematicaServiceTest();
		test.testFindAll();
		//test.testFindByLibroId();
	}
}
