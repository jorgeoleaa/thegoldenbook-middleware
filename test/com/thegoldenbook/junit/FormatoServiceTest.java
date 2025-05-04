package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Format;
import com.thegoldenbook.service.FormatService;
import com.thegoldenbook.service.impl.FormatServiceImpl;

public class FormatoServiceTest {

	private FormatService formatoService = null;
	
	public FormatoServiceTest() {
		formatoService = new FormatServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		String locale="it";
		List<Format> formatos = formatoService.findAll(locale);
		assertEquals(4, formatos.size());
	}

}
