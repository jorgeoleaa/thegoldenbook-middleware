package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Idioma;
import com.pinguela.thegoldenbook.service.IdiomaService;
import com.pinguela.thegoldenbook.service.impl.IdiomaServiceImpl;

public class IdiomaServiceTest {

	private IdiomaService idiomaService = null;
	
	public IdiomaServiceTest() {
		idiomaService = new IdiomaServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Idioma> idiomas = idiomaService.findAll("es");
		assertEquals(5, idiomas.size());
	}
	
}
