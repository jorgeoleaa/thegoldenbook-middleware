package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Localidad;
import com.pinguela.thegoldenbook.service.LocalidadService;
import com.pinguela.thegoldenbook.service.impl.LocalidadServiceImpl;

public class LocalidadServiceTest {

	private LocalidadService localidadService = null;
	
	public LocalidadServiceTest() {
		localidadService = new LocalidadServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Localidad> localidades= localidadService.findAll();
		assertEquals(35, localidades.size());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Localidad l = localidadService.findById(1);
		assertEquals(1, l.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Localidad l = localidadService.findById(100);
		assertEquals(null, l);
	}
	
	@Test
	public void testFindByCodigoPostal01() throws Exception{
		Localidad l = localidadService.findByCodigoPostal(27510);
		assertEquals(27510, l.getCodigoPostal());
	}
	
	@Test
	public void testFindByCodigoPostal02() throws Exception{
		Localidad l = localidadService.findByCodigoPostal(10000);
		assertEquals(null, l);
	}

}
