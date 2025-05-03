package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Locality;
import com.thegoldenbook.service.LocalityService;
import com.thegoldenbook.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {

	private static Logger logger = LogManager.getLogger(LocalidadServiceTest.class);
	private LocalityService localidadService = null;

	public LocalidadServiceTest() {
		localidadService = new LocalidadServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing FindAll...");
		List<Locality> resultados = localidadService.findAll();

		if(resultados.isEmpty()) {
			logger.info("No se han encontrado resultados");
		}else {
			for(Locality l : resultados) {
				logger.info(resultados);
			}
		}
	}

	public void testFindByCodigoPostal() throws Exception{
		logger.traceEntry("Testing findByCodigoPostal...");
		Locality l = localidadService.findByCodigoPostal(27510);
		if(l != null) {
			logger.info(l);
		}else {
			logger.info("No se han encontrado resultados");
		}


	}

	public void testFindById() throws Exception{
		logger.traceEntry("Testing findByLocalidadId...");
		Locality l = localidadService.findById(4);
		if(l != null) {
			logger.info(l);
		}else {
			logger.info("No se han encontrado resultados");
		}

	}

	public static void main(String[]args) throws Exception{
		LocalidadServiceTest test = new LocalidadServiceTest();
		test.testFindAll();
		//test.testFindByCodigoPostal();
		//test.testFindById();

	}
}
