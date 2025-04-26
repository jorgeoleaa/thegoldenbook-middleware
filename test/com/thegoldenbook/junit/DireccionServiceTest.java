package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Address;
import com.thegoldenbook.service.DireccionService;
import com.thegoldenbook.service.EmpleadoService;
import com.thegoldenbook.service.impl.DireccionServiceImpl;
import com.thegoldenbook.service.impl.EmpleadoServiceImpl;

public class DireccionServiceTest {
	
	private DireccionService direccionService = null;
	private EmpleadoService empleadoService = null;
	
	public DireccionServiceTest(){
		direccionService = new DireccionServiceImpl();
		empleadoService = new EmpleadoServiceImpl();
	}
	
	@Test
	public void testDelete() throws Exception{
		boolean delete = direccionService.delete(12l);
		assertEquals(true, delete);
	}
	
	@Test
	public void testCreate() throws Exception{
		Address direccion = new Address();
		direccion.setNombreVia("Calle Santiago");
		direccion.setDirVia("n12 bajo");
		direccion.setLocalidadId(4);
		direccion.setClienteId(7l);
		Long id = direccionService.create(direccion);
		assertEquals(19, id);
	}
	
	@Test
	public void testUpdate() throws Exception{
		Address direccion = empleadoService.findBy(5l).getDireccion();
		direccion.setNombreVia("Calle Rodolfo reno");
		direccionService.update(direccion);
		assertNotEquals(direccion, empleadoService.findBy(5l).getDireccion());
	}
}

