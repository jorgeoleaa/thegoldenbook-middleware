package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.ClasificacionEdad;
import com.thegoldenbook.service.ClasificacionEdadService;
import com.thegoldenbook.service.impl.ClasificacionEdadServiceImpl;

public class ClasificacionEdadServiceTest {

	private ClasificacionEdadService clasificacionEdadService = null;
	
	public ClasificacionEdadServiceTest () {
		clasificacionEdadService = new ClasificacionEdadServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<ClasificacionEdad> clasificacionesEdad = clasificacionEdadService.findAll("es");
		assertEquals(3, clasificacionesEdad.size());
	}

}
