package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.LineaPedido;
import com.thegoldenbook.service.LineaPedidoService;
import com.thegoldenbook.service.impl.LineaPedidoServiceImpl;

public class LineaPedidoServiceTest {

	private LineaPedidoService lineaPedidoService = null;
	
	public LineaPedidoServiceTest() {
		lineaPedidoService = new LineaPedidoServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		LineaPedido l  = lineaPedidoService.findById(3l);
		assertEquals(3, l.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		LineaPedido l  = lineaPedidoService.findById(100l);
		assertNull(l);
	}

}
