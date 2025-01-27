package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.ValoracionService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.service.impl.ValoracionServiceImpl;

public class ClienteServiceTest {
	
	
	private PedidoService pedidoService = null;
	private ValoracionService valoracionService = null;
	private ClienteService clienteService = null;
	
	public ClienteServiceTest() {
		clienteService = new ClienteServiceImpl();
		valoracionService = new ValoracionServiceImpl();
		pedidoService = new PedidoServiceImpl();
	}

	@Test
	public void testFindAll() throws Exception{
		Results<ClienteDTO> clientes = clienteService.findAll(1, Integer.MAX_VALUE);
		assertEquals(8, clientes.getTotal());
	}
	
	@Test
	public void testFindById01() throws Exception{
		ClienteDTO c = clienteService.findById(3l);
		assertEquals(4l, c.getId());
	}
	
	@Test
	public void testFindById02()throws Exception{
		ClienteDTO c = clienteService.findById(0l);
		assertEquals(null, c);
	}
	
	@Test
	public void testFindByNick01() throws Exception{
		ClienteDTO c = clienteService.findByNick("user1");
		assertEquals("user1", c.getNickname());
	}
	
	@Test
	public void testFindByNick02() throws Exception{
		ClienteDTO c = clienteService.findByNick("user10000");
		assertEquals(null, c);
	}
	
	@Test
	public void testFindByEmail01() throws Exception{
		ClienteDTO c = clienteService.findByEmail("juan@gmail.com");
		assertEquals("juan@gmail.com", c.getEmail());
	}
	
	@Test
	public void testFindByEmail02() throws Exception{
		ClienteDTO c = clienteService.findByEmail("dafdfa@gmail.com");
		assertEquals(null, c);
	}
	
	@Test
	public void testRegistrar() throws Exception{
		ClienteDTO c = new ClienteDTO();
		c.setNombre("Jorge");
		c.setApellido1("Olea");
		c.setApellido2("Dos santos");
		c.setDniNie("44444444M");
		c.setNickname("userPereira");
		c.setPassword("holaquetal");
		c.setTelefono("77788899");
		c.setEmail("joroleacasanova@gmail.com");
		c.setPassword("hola");
		
		long id = clienteService.registrar(c);
		assertNotNull(id);
	}
	
	@Test
	public void testUpdate() throws Exception{
		ClienteDTO c = clienteService.findById(4l);
		c.setNombre("Marta");
		clienteService.update(c);
		assertNotEquals(clienteService.findById(4l), c);
	}
	
	@Test
	public void testUpdatePassword() throws Exception{
		clienteService.updatePassword("holaquetalestas", 1l);
		assertNotEquals(clienteService.findById(1l).getPassword(),"clave_segura");
	}
	
	@Test
	public void testDelete() throws Exception{
		assertTrue(clienteService.delete(9l));
	}
}
