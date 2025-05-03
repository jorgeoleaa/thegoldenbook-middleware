package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Address;
import com.thegoldenbook.service.AddressService;
import com.thegoldenbook.service.EmployeeService;
import com.thegoldenbook.service.impl.AddressServiceImpl;
import com.thegoldenbook.service.impl.EmpleadoServiceImpl;

public class AddressServiceTest {
	
	private AddressService addressService = null;
	private EmployeeService employeeService = null;
	
	public AddressServiceTest(){
		addressService = new AddressServiceImpl();
		employeeService = new EmpleadoServiceImpl();
	}
	
	@Test
	public void testDelete() throws Exception{
		boolean delete = addressService.delete(12l);
		assertEquals(true, delete);
	}
	
	@Test
	public void testCreate() throws Exception{
		Address address = new Address();
		address.setStreetName("Calle Santiago");
		address.setAddressLine2("n12 bajo");
		address.setRegionId(4);
		address.setUserId(7l);
		Long id = addressService.create(address);
		assertEquals(19, id);
	}
	
	@Test
	public void testUpdate() throws Exception{
		Address address = employeeService.findBy(5l).getAddress();
		address.setStreetName("Calle Rodolfo reno");
		addressService.update(address);
		assertNotEquals(address, employeeService.findBy(5l).getAddress());
	}
}

