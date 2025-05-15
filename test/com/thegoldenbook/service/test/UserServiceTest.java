package com.thegoldenbook.service.test; 

import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.User;
import com.thegoldenbook.service.UserService;
import com.thegoldenbook.service.impl.UserServiceImpl;

public class UserServiceTest {

	private static Logger logger = LogManager.getLogger(UserServiceTest.class);
	private UserService userService = null;

	public UserServiceTest() {
		userService = new UserServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll....");
		
		String locale = "es_ES";
		
		Results<User> users = userService.findAll(locale, 1, 5);

		for(User c : users.getPage()) {
			logger.info(c);
		}
	}

	public void testFindById() throws Exception{

		logger.traceEntry("Testing findById...");
		String locale = "es_ES";
		User user = new User();
		user = userService.findById(1l, locale);

		logger.info(user);

	}

	public void testFindByNick() throws Exception{

		logger.traceEntry("Testing findByNickname...");
		String locale = "es_ES";
		User user = new User();
		user = userService.findByNick("bookworm88", locale);

		logger.info(user);
	}

	public void testFindByEmail() throws Exception{

		logger.traceEntry("Testing findByEmail...");
		String locale = "es_ES";
		User user = new User();
		user = userService.findByEmail("laura.gomez@example.es", locale);
		logger.info(user);
	}

	public void testRegister() throws Exception{

		logger.traceEntry("Testing register...");

		User user = new User();

		user.setNickname("yorch");
		user.setName("Jorge");
		user.setLastName("Olea");
		user.setSecondLastName("Casanova");
		user.setNationalId("34567890D");
		user.setEmail("jorgeoleacasanova@gmail.com");
		user.setPhoneNumber("768098654");
		user.setPassword("abc123.");
		user.setCreatedAt(new Date(System.currentTimeMillis()));

		userService.register(user);

		logger.trace("The following user created an account: "+user);


	}

	public void testDelete() throws Exception{

		logger.traceEntry("Testing delete...");
		String locale = "es_ES";
		User user = new User();
		user.setId(10l);
		userService.delete(user.getId(), locale);
		logger.trace("Deleted client: "+user);

	}

	public void testUpdate() throws Exception{

		User cliente = new User();

		logger.traceEntry("Testing update...");

		cliente = userService.findById(4l);

		if(cliente.getId() != null) {
			cliente.setNombre("Ana");
			userService.update(cliente);
			logger.trace("Los datos del cliente han sido actualizados correctamente");
		}else {
			logger.trace("No se han encontrado clientes con el id proporcionado");
		}

		
	}

	public void testUpdatePassword() throws Exception{
		logger.traceEntry("Testing updatePassword...");
		String password = "Escairon718";
		Long id = 11l;
		if (userService.updatePassword(password, id)) {
			logger.info("Su contraseña ha sido actualizada correctamente");
		}else{
			logger.info("Su contraseña no ha sido actualizada");
		}
			
	}
	
	public void testAutenticacionOK() throws Exception{

		logger.trace("Testing Autenticacion de usuario y password correctas...");


			User e = userService.autenticar("joroleacasanova@gmail.com", "abc123.");

			if (e!=null) {
				logger.trace("Autenticación correcta. Todo OK!");
				logger.trace(e);
			} else {
				logger.trace("Fallo en el método de autenticación con usuario y password correctos.");
			}
		
	}

	public static void main(String [] args) throws Exception{

		UserServiceTest test = new UserServiceTest();
		//test.testFindAll();
		//test.testFindById();
		//test.testFindByNick();
		//test.testFindByEmail();
		//test.testRegister();
		//test.testUpdate();
		//test.testUpdatePassword();
		test.testDelete();
		//test.testAutenticacionOK();

	}

}
