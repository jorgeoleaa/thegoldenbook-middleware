package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Formato;
import com.pinguela.thegoldenbook.service.FormatoService;
import com.pinguela.thegoldenbook.service.impl.FormatoServiceImpl;

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
