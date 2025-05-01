package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Country;
import com.thegoldenbook.service.CountryService;
import com.thegoldenbook.service.impl.CountryServiceImpl;

public class CountryServiceTest {

	private CountryService countryService = null;
	
	public CountryServiceTest() {
		countryService = new CountryServiceImpl();
	}
	@Test
	public void testFindAll() throws Exception{
		List<Country> countries = countryService.findAll("es_ES");
		assertEquals(248, countries.size());
	}
	
	@Test 
	public void testFindById() throws Exception{
		Country country = countryService.findById(5, "es_ES");
		assertEquals(5, country.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Country country = countryService.findById(249, "es_ES");
		assertEquals(null, country);
	}
	
}
