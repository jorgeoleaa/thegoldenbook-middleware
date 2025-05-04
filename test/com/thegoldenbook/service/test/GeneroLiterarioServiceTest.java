package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.LiteraryGenre;
import com.thegoldenbook.service.LiteraryGenreService;
import com.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;

public class GeneroLiterarioServiceTest {
	
	private Logger logger = LogManager.getLogger(GeneroLiterarioServiceTest.class);
	private LiteraryGenreService generoLiterarioService = null;
	
	public GeneroLiterarioServiceTest() {
		generoLiterarioService = new GeneroLiterarioServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		String locale = "it";
		List<LiteraryGenre> generos = generoLiterarioService.findAll(locale);
		
		if(generos.isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(LiteraryGenre gl : generos) {
				logger.info(gl);
			}
		}
		
	}
	
	public static void main(String [] args) throws Exception{
		GeneroLiterarioServiceTest test = new GeneroLiterarioServiceTest();
		test.testFindAll();
	}
}
