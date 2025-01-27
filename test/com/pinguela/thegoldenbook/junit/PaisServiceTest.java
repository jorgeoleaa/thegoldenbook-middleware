package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Pais;
import com.pinguela.thegoldenbook.service.PaisService;
import com.pinguela.thegoldenbook.service.impl.PaisServiceImpl;

public class PaisServiceTest {

	private PaisService paisService = null;
	
	public PaisServiceTest() {
		paisService = new PaisServiceImpl();
	}
	@Test
	public void testFindAll() throws Exception{
		List<Pais> paises = paisService.findAll();
		assertEquals(248, paises.size());
	}
	
	@Test 
	public void testFindById() throws Exception{
		Pais p = paisService.findById(5);
		assertEquals(5, p.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Pais p = paisService.findById(249);
		assertEquals(null, p);
	}
	
}
