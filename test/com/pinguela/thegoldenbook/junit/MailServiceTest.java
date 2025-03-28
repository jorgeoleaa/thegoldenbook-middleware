package com.pinguela.thegoldenbook.junit;


import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.dao.DataException;
import com.pinguela.thegoldenbook.model.ClienteDTO;
import com.pinguela.thegoldenbook.service.ClienteService;
import com.pinguela.thegoldenbook.service.MailException;
import com.pinguela.thegoldenbook.service.MailService;
import com.pinguela.thegoldenbook.service.impl.ClienteServiceImpl;
import com.pinguela.thegoldenbook.service.impl.MailServiceImpl;

public class MailServiceTest {
	
	private static Logger logger = LogManager.getLogger(com.pinguela.thegoldenbook.service.MailServiceTest.class);
	
	private MailService mailService = null;
	private ClienteService clienteService = null;
	
	public MailServiceTest() {
		mailService = new MailServiceImpl();
		clienteService = new ClienteServiceImpl();
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
			 ClienteDTO cliente = clienteService.findByEmail("jorgeoleacasanova@gmail.com");
			 mailService.sendBienvenida(cliente.getEmail(), cliente);
			 System.out.println("Mail enviado!");
		 }catch(MailException pe) {
			 fail("Email no enviado");
		 }
	 }
}
