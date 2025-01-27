package com.pinguela.thegoldenbook.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.Autor;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.impl.AutorServiceImpl;
import com.pinguela.thegoldenbook.util.DateUtils;

public class AutorServiceTest {
	
	private static Logger logger = LogManager.getLogger(AutorServiceTest.class);
	private AutorService autorService = null;
	
	public AutorServiceTest() {
		autorService = new AutorServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		Results<Autor> autores = autorService.findAll(1, 500);
		
		for(Autor a : autores.getPage()) {
			logger.trace(a);
		}
	}
	
	public void testFindByAutorId() throws Exception{
		logger.traceEntry("Testing findByAutorId...");
		Autor a = autorService.findByAutor(2l);
		
		if(a.getId() == null) {
			logger.trace("No se han encontrado autores a partir del ID proporcionado");
		}else {
			logger.info(a);
		}
		
	}
	
	public void testFindByLibroId() throws Exception{
		logger.traceEntry("Testing findByLibroId...");
		List<Autor> resultados = autorService.findByLibro(3l);
		
		if(resultados.isEmpty()) {
			logger.trace("No se han encontrado autores para el ID de libro proporcionado");
		}else {
			logger.info(resultados);
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Autor a = new Autor();
		a.setNombre("Haruki");
		a.setApellido1("Murakami");
		a.setFechaNacimiento(DateUtils.getDate(1949, 0, 12));
		a.setApellido2(null);
		autorService.create(a);
		
		logger.trace("El autor con ID "+a.getId()+" ha sido creado correctamente");
		
		
	}
	
	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Autor a = autorService.findByAutor(29l);
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
