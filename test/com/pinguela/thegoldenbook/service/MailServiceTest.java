package com.pinguela.thegoldenbook.service;

import com.pinguela.thegoldenbook.service.impl.MailServiceImpl;

public class MailServiceTest {

	private MailService mailService = null;

	public MailServiceTest() {
		mailService = new MailServiceImpl();
	}

	public void testEnviarEmailSimple() {

		try {
			mailService.enviar("jorgeoleacasanova@gmail.com", "Testing email", "Estoy testeando si funciona el MailService");
			System.out.println("Email enviado!");
			
		} catch(MailException e) {
			System.out.println("Email no enviado");
		}
	}
	
	public static void main (String [] args) {
		MailServiceTest test = new MailServiceTest();
		test.testEnviarEmailSimple();
	}
}
