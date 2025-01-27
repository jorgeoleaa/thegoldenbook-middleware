package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Provincia;
import com.pinguela.thegoldenbook.service.ProvinciaService;
import com.pinguela.thegoldenbook.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	
	private ProvinciaService provinciaService = null;
	
	public ProvinciaServiceTest() {
		provinciaService = new ProvinciaServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Provincia> provincias = provinciaService.findAll();
		assertEquals(49, provincias.size());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Provincia p = provinciaService.findById(5);
		assertEquals(5, p.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Provincia p = provinciaService.findById(100);
		assertEquals(null, p);
	}
}
