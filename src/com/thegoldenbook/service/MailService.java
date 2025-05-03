package com.thegoldenbook.service;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Order;

public interface MailService {
		
	public void send(String to, String subject, String body)
		throws MailException;

	public void sendWelcome(String to, User user) throws MailException;
	
	public void sendPedidoRealizado(String to, User user, Order order) throws MailException ;
}
