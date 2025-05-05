package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.OrderItem;
import com.thegoldenbook.service.OrderItemService;
import com.thegoldenbook.service.impl.OrderItemServiceImpl;

public class LineaPedidoServiceTest {

	private OrderItemService lineaPedidoService = null;
	
	public LineaPedidoServiceTest() {
		lineaPedidoService = new OrderItemServiceImpl();
	}
	
	@Test
	public void testFindById01() throws Exception{
		OrderItem l  = lineaPedidoService.findById(3l);
		assertEquals(3, l.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		OrderItem l  = lineaPedidoService.findById(100l);
		assertNull(l);
	}

}
