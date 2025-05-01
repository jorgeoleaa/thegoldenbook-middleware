package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.ReadingAgeGroup;
import com.thegoldenbook.service.ReadingAgeGroupService;
import com.thegoldenbook.service.impl.ClasificacionEdadServiceImpl;

public class ClasificacionEdadServiceTest {

	private static Logger logger = LogManager.getLogger(ClasificacionEdadServiceTest.class);
	private ReadingAgeGroupService clasificacionEdadService = null;

	public ClasificacionEdadServiceTest() {
		clasificacionEdadService = new ClasificacionEdadServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		String locale = "it";
		List<ReadingAgeGroup> resultados = clasificacionEdadService.findAll(locale);
		if(resultados.isEmpty()) {
			logger.trace("no se han encontrado resultados");
		}else {
			for(ReadingAgeGroup ce : resultados) {
				logger.info(ce);
			}
		}
	}


	public static void main(String [] args) throws Exception{
		ClasificacionEdadServiceTest test = new ClasificacionEdadServiceTest();
		test.testFindAll();
	}
}
