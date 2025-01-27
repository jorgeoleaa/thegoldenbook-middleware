package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Tematica;
import com.pinguela.thegoldenbook.service.TematicaService;
import com.pinguela.thegoldenbook.service.impl.TematicaServiceImpl;

public class TematicaServiceTest {
	
	private TematicaService tematicaService = null;
	
	public TematicaServiceTest() {
		tematicaService = new TematicaServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Tematica> tematicas = tematicaService.findAll("es");
		assertEquals(17, tematicas.size());
	}
	
	@Test
	public void testFindByLibroId01() throws Exception {
		String locale = "it";
		List<Tematica> tematicas = tematicaService.findByLibro(locale, 3l);
		assertEquals(3, tematicas.size());
	}
	
	@Test 
	public void testFindByLibroId02() throws Exception{
		String locale = "it";
		List<Tematica> tematicas = tematicaService.findByLibro(locale, 0l);
		assertTrue(tematicas.isEmpty(), "Tematicas del libro proporcionado");
	}

}
