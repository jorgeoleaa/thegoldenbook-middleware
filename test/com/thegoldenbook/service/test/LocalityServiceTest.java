package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Locality;
import com.thegoldenbook.service.LocalityService;
import com.thegoldenbook.service.impl.LocalityServiceImpl;

public class LocalityServiceTest {

	private static Logger logger = LogManager.getLogger(LocalityServiceTest.class);
	private LocalityService localityService = null;

	public LocalityServiceTest() {
		localityService = new LocalityServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing FindAll...");
		List<Locality> resultados = localityService.findAll("es_ES");

		if(resultados.isEmpty()) {
			logger.info("There are no results");
		}else {
			for(Locality l : resultados) {
				logger.info(resultados);
			}
		}
	}

	public void testFindByCodigoPostal() throws Exception{
		logger.traceEntry("Testing findByCodigoPostal...");
		Locality l = localityService.findByPostalCode("27510", "es_ES");
		if(l != null) {
			logger.info(l);
		}else {
			logger.info("There are no results");
		}


	}

	public void testFindById() throws Exception{
		logger.traceEntry("Testing findByLocalidadId...");
		Locality l = localityService.findById(4, "es_ES");
		if(l != null) {
			logger.info(l);
		}else {
			logger.info("There are no results");
		}

	}

	public static void main(String[]args) throws Exception{
		LocalityServiceTest test = new LocalityServiceTest();
		test.testFindAll();
		//test.testFindByPostalCode();
		//test.testFindById();

	}
}
