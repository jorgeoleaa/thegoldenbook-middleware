package com.pinguela.thegoldenbook.junit;


import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.service.MailException;
import com.pinguela.thegoldenbook.service.MailService;
import com.pinguela.thegoldenbook.service.impl.MailServiceImpl;

public class MailServiceTest {
	
	MailService mailService = null;
	
	public MailServiceTest() {
		mailService = new MailServiceImpl();
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
}
