package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Language;
import com.thegoldenbook.service.LanguageService;
import com.thegoldenbook.service.impl.LanguageServiceImpl;

public class IdiomaServiceTest {

	private LanguageService idiomaService = null;
	
	public IdiomaServiceTest() {
		idiomaService = new LanguageServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Language> idiomas = idiomaService.findAll("es");
		assertEquals(5, idiomas.size());
	}
	
}
