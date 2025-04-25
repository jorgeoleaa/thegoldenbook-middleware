package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Autor;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.service.AutorService;
import com.thegoldenbook.service.impl.AutorServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class AutorServiceTest {

	private AutorService autorService = null;
	
	public AutorServiceTest() {
		autorService = new AutorServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		Results<Autor> autores = autorService.findAll(1, Integer.MAX_VALUE);
		assertEquals(28, autores.getTotal());
	}
	
	@Test
	public void testFindByAutorId01() throws Exception{
		Autor a = autorService.findByAutor(2l);
		assertEquals(2, a.getId());
	}
	
	@Test
	public void testFindByAutorId02() throws Exception{
		Autor a = autorService.findByAutor(0l);
		assertEquals(null, a);
	}
	
	@Test
	public void testFindByLibro01() throws Exception{
		List<Autor> autores = autorService.findByLibro(3l);
		assertEquals(1, autores.size());
	}
	@Test
	public void testFindByLibro02() throws Exception{
		List<Autor> autores = autorService.findByLibro(0l);
		assertEquals(0, autores.size());
	}
	
	@Test
	public void testUpdate() throws Exception{
		Autor a = autorService.findByAutor(2l);
		a.setNombre("AAAAAAAAAAAAAAAAAA");
		autorService.update(a);
		assertEquals(a.getNombre(), autorService.findByAutor(2l).getNombre());
	}
	
	@Test
	public void testCreate() throws Exception{
		Autor a = new Autor();
		a.setId(34l);
		a.setNombre("Michael");
		a.setApellido1("Connelly");
		a.setFechaNacimiento(DateUtils.getDate(1956, 06, 21));
		Long id = autorService.create(a);
		
		assertNotNull(id);
	}
}
