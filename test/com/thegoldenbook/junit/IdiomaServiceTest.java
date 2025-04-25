package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Idioma;
import com.thegoldenbook.service.IdiomaService;
import com.thegoldenbook.service.impl.IdiomaServiceImpl;

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
