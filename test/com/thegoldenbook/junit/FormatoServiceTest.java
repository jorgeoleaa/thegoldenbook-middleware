package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Formato;
import com.thegoldenbook.service.FormatoService;
import com.thegoldenbook.service.impl.FormatoServiceImpl;

public class FormatoServiceTest {

	private FormatoService formatoService = null;
	
	public FormatoServiceTest() {
		formatoService = new FormatoServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		String locale="it";
		List<Formato> formatos = formatoService.findAll(locale);
		assertEquals(4, formatos.size());
	}

}
