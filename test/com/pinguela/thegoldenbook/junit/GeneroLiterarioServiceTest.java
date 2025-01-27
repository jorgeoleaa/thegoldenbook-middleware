package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.GeneroLiterario;
import com.pinguela.thegoldenbook.service.GeneroLiterarioService;
import com.pinguela.thegoldenbook.service.impl.GeneroLiterarioServiceImpl;

public class GeneroLiterarioServiceTest {

	private GeneroLiterarioService generoLiterarioService = null;
	
	public GeneroLiterarioServiceTest() {
		generoLiterarioService = new GeneroLiterarioServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception {
		String locale = "it";
		List<GeneroLiterario> generos = generoLiterarioService.findAll(locale);
		assertEquals(5, generos.size());
	}

}
