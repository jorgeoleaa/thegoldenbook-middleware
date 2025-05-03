package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Locality;
import com.thegoldenbook.service.LocalityService;
import com.thegoldenbook.service.impl.LocalityServiceImpl;

public class LocalidadServiceTest {

	private LocalityService localidadService = null;
	
	public LocalidadServiceTest() {
		localidadService = new LocalityServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Locality> localidades= localidadService.findAll();
		assertEquals(35, localidades.size());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Locality l = localidadService.findById(1);
		assertEquals(1, l.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Locality l = localidadService.findById(100);
		assertEquals(null, l);
	}
	
	@Test
	public void testFindByCodigoPostal01() throws Exception{
		Locality l = localidadService.findByCodigoPostal(27510);
		assertEquals(27510, l.getCodigoPostal());
	}
	
	@Test
	public void testFindByCodigoPostal02() throws Exception{
		Locality l = localidadService.findByCodigoPostal(10000);
		assertEquals(null, l);
	}

}
