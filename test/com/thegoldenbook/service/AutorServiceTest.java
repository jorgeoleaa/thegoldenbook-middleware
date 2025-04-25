package com.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.AutorService;
import com.thegoldenbook.service.impl.AutorServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class AutorServiceTest {
	
	private static Logger logger = LogManager.getLogger(AutorServiceTest.class);
	private AutorService autorService = null;
	
	public AutorServiceTest() {
		autorService = new AutorServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		Results<Author> autores = autorService.findAll(1, 500);
		
		for(Author a : autores.getPage()) {
			logger.trace(a);
		}
	}
	
	public void testFindByAutorId() throws Exception{
		logger.traceEntry("Testing findByAutorId...");
		Author a = autorService.findByAutor(2l);
		
		if(a.getId() == null) {
			logger.trace("No se han encontrado autores a partir del ID proporcionado");
		}else {
			logger.info(a);
		}
		
	}
	
	public void testFindByLibroId() throws Exception{
		logger.traceEntry("Testing findByLibroId...");
		List<Author> resultados = autorService.findByLibro(3l);
		
		if(resultados.isEmpty()) {
			logger.trace("No se han encontrado autores para el ID de libro proporcionado");
		}else {
			logger.info(resultados);
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Author a = new Author();
		a.setNombre("Haruki");
		a.setApellido1("Murakami");
		a.setFechaNacimiento(DateUtils.getDate(1949, 0, 12));
		a.setApellido2(null);
		autorService.create(a);
		
		logger.trace("El autor con ID "+a.getId()+" ha sido creado correctamente");
		
		
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Author a = autorService.findByAutor(29l);
		a.setApellido1("aaaaaa");
		autorService.update(a);
		logger.trace("El autor con ID "+a.getId()+" ha sido actualizado correctamente");
	}
	
	public static void main (String [] args) throws Exception{
		
		AutorServiceTest autorTest = new AutorServiceTest();
		//autorTest.testFindAll();
		autorTest.testFindByAutorId();
		//autorTest.testFindByLibroId();
		//autorTest.testCreate();
		//autorTest.testUpdate();
	}
}
