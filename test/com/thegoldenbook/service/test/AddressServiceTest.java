package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Address;
import com.thegoldenbook.model.Employee;
import com.thegoldenbook.service.UserService;
import com.thegoldenbook.service.AddressService;
import com.thegoldenbook.service.EmployeeService;
import com.thegoldenbook.service.impl.UserServiceImpl;
import com.thegoldenbook.service.impl.AddressServiceImpl;
import com.thegoldenbook.service.impl.EmployeeServiceImpl;

public class AddressServiceTest {
	
	private static Logger logger = LogManager.getLogger(AddressServiceTest.class);
	private AddressService addressService = null;
	private UserService userService = null;
	private EmployeeService employeeService = null;
	
	public AddressServiceTest() {
		addressService = new AddressServiceImpl();
		userService = new UserServiceImpl();
		employeeService = new EmployeeServiceImpl();
	}
	
	public void testDelete() throws Exception{
		
		logger.traceEntry("Testing deleteByUser...");
		Address direccion = new Address();
		direccion.setId(22l);
		
		if(addressService.delete(direccion.getId())){
			logger.trace("The address with ID: "+direccion.getId()+" has been deleted correctly");
		}else {
			logger.trace("The address has not been deleted");
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		Address d = new Address();
		d.setStreetName("Plaza La Concepción");
		d.setAddressLine2("nº7");
		d.setLocalityId(15);
		d.setUserId(null);
		d.setEmployeeId(7l);
		addressService.create(d);
		logger.trace("Created the address with id: "+d.getId());
		
	}
	
	public void testUpdateByEmployee() throws Exception{
		
		logger.traceEntry("Testing Update...");
		String locale = "es_ES";
		Employee e = employeeService.findBy(7l, locale);
		Address address =  e.getAddress();
		address.setStreetName("403 Elm Street");
		
		if(addressService.update(address)) {
			logger.trace("The address was updated correctly");
		}else {
			logger.trace("The address has not been updated");
		}
	}
	
	public void testUpdateByUser() throws Exception{
		logger.traceEntry("Testing UpdateByEmployee...");
		User user = userService.findById(2l);
		List<Address> addresses = user.getAddresses();
		for(Address address : addresses) {
			address.setStreetName("Avenida Camariñas");
			addressService.update(address);
		}
		
	}
	
	public static void main(String [] args) throws Exception{
		
		AddressServiceTest test = new AddressServiceTest();
		//test.testDelete();
		//test.testCreate();
		//test.testUpdateByEmployee();
		test.testUpdateByUser();
	}

}
