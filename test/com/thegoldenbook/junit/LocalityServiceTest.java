package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Locality;
import com.thegoldenbook.service.LocalityService;
import com.thegoldenbook.service.impl.LocalityServiceImpl;

public class LocalityServiceTest {

	private LocalityService localityService = null;
	
	public LocalityServiceTest() {
		localityService = new LocalityServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Locality> localities = localityService.findAll("es_ES");
		assertEquals(35, localities.size());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Locality l = localityService.findById(1, "es_ES");
		assertEquals(1, l.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Locality l = localityService.findById(100, "es_ES");
		assertEquals(null, l);
	}
	
	@Test
	public void testFindByPostalCode01() throws Exception{
		Locality l = localityService.findByPostalCode("27510", "es_ES");
		assertEquals(27510, l.getPostalCode());
	}
	
	@Test
	public void testFindByPostalCode02() throws Exception{
		Locality l = localityService.findByPostalCode("10000", "es_ES");
		assertEquals(null, l);
	}

}
