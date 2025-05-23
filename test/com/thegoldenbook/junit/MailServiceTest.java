package com.thegoldenbook.junit;


import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.thegoldenbook.dao.DataException;
import com.thegoldenbook.model.User;
import com.thegoldenbook.service.UserService;
import com.thegoldenbook.service.MailException;
import com.thegoldenbook.service.MailService;
import com.thegoldenbook.service.impl.UserServiceImpl;
import com.thegoldenbook.service.impl.MailServiceImpl;

public class MailServiceTest {
	
	private static Logger logger = LogManager.getLogger(com.thegoldenbook.service.test.MailServiceTest.class);
	
	private MailService mailService = null;
	private UserService clienteService = null;
	
	public MailServiceTest() {
		mailService = new MailServiceImpl();
		clienteService = new UserServiceImpl();
	}
	
	 @Test
	    public void testEnviarEmailSimple() {
	        try {
	            mailService.enviar("joroleacasanova@gmail.com", "Testing email", "Estoy testeando si funciona el MailService");
	            System.out.println("Email enviado!");
	        } catch(MailException e) {
	            fail("Email no enviado");
	        }
	    }
	 
	 @Test
	 public void testSendBienvenida() throws DataException {
		 try {
			 User cliente = clienteService.findByEmail("jorgeoleacasanova@gmail.com");
			 mailService.sendBienvenida(cliente.getEmail(), cliente);
			 System.out.println("Mail enviado!");
		 }catch(MailException pe) {
			 fail("Email no enviado");
		 }
	 }
}
