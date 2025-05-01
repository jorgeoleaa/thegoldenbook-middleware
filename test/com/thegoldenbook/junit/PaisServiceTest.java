package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Country;
import com.thegoldenbook.service.CountryService;
import com.thegoldenbook.service.impl.CountryServiceImpl;

public class PaisServiceTest {

	private CountryService paisService = null;
	
	public PaisServiceTest() {
		paisService = new CountryServiceImpl();
	}
	@Test
	public void testFindAll() throws Exception{
		List<Country> paises = paisService.findAll();
		assertEquals(248, paises.size());
	}
	
	@Test 
	public void testFindById() throws Exception{
		Country p = paisService.findById(5);
		assertEquals(5, p.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Country p = paisService.findById(249);
		assertEquals(null, p);
	}
	
}
