package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.service.ClienteService;
import com.thegoldenbook.service.AddressService;
import com.thegoldenbook.service.EmpleadoService;
import com.thegoldenbook.service.impl.ClienteServiceImpl;
import com.thegoldenbook.service.impl.AddressServiceImpl;
import com.thegoldenbook.service.impl.EmpleadoServiceImpl;

public class AddressServiceTest {
	
	private static Logger logger = LogManager.getLogger(AddressServiceTest.class);
	private AddressService addressService = null;
	private ClienteService userService = null;
	private EmpleadoService employeeService = null;
	
	public AddressServiceTest() {
		addressService = new AddressServiceImpl();
		userService = new ClienteServiceImpl();
		employeeService = new EmpleadoServiceImpl();
	}
	
	public void testDelete() throws Exception{
		
		logger.traceEntry("Testing deleteByUser...");
		Address direccion = new Address();
		direccion.setId(12l);
		
		if(addressService.delete(direccion.getId())){
			logger.trace("The address with ID: "+direccion.getId()+" has been deleted correctly");
		}else {
			logger.trace("The address has not been deleted");
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Address d = new Address();
		d.setStreetName("Plaza de la Concepción");
		d.setAddressLine2("nº7");
		d.setLocalityId(24);
		d.setUserId(null);
		d.setEmployeeId(7l);
		addressService.create(d);
		logger.trace("Created the address with id: "+d.getId());
		
	}
	
	public void testUpdateByEmployee() throws Exception{
		
		logger.traceEntry("Testing Update...");
		Employee e = employeeService.findBy(7l);
		Address address =  e.getAddress();
		address.setStreetName("Plaza Mayorista");
		
		if(addressService.update(address)) {
			logger.trace("The address has not been created");
		}else {
			logger.trace("The address was created correctly");
		}
		
		

	}
	
	public void testUpdateByUser() throws Exception{
		logger.traceEntry("Testing UpdateByEmployee...");
		User user = new User();
		user = userService.findById(1l);
		List<Address> addresses = user.getAddresses();
		for(Address address : addresses) {
			address.setStreetName("Avenida Camariñas");
			addressService.update(address);
		}
		
	}
	
	public static void main(String [] args) throws Exception{
		
		AddressServiceTest test = new AddressServiceTest();
		//test.testDelete();
		test.testCreate();
		//test.testUpdateByEmployee();
		//test.testUpdateByUser();
	}

}
