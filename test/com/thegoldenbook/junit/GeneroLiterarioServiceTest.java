package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.LiteraryGenre;
import com.thegoldenbook.service.LiteraryGenreService;
import com.thegoldenbook.service.impl.LiteraryGenreServiceImpl;

public class GeneroLiterarioServiceTest {

	private LiteraryGenreService generoLiterarioService = null;
	
	public GeneroLiterarioServiceTest() {
		generoLiterarioService = new LiteraryGenreServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception {
		String locale = "it";
		List<LiteraryGenre> generos = generoLiterarioService.findAll(locale);
		assertEquals(5, generos.size());
	}

}
